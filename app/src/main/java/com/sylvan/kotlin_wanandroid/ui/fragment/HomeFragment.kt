package com.sylvan.kotlin_wanandroid.ui.fragment

import Constant
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.adapter.HomeAdapter
import com.sylvan.kotlin_wanandroid.base.BaseMvpFragment
import com.sylvan.kotlin_wanandroid.bean.BannerResponse
import com.sylvan.kotlin_wanandroid.bean.Datas
import com.sylvan.kotlin_wanandroid.bean.HomeListResponse
import com.sylvan.kotlin_wanandroid.mvp.contract.HomeContract
import com.sylvan.kotlin_wanandroid.mvp.presenter.HomePresenter
import com.sylvan.kotlin_wanandroid.ui.WebViewAct
import com.sylvan.kotlin_wanandroid.widget.RecyclerViewBanner
import inflater
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_banner.*
import toast

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.ui.fragment
 * @Author: sylvan
 * @Date: 19-7-25
 */
class HomeFragment : BaseMvpFragment<HomeContract.View, HomeContract.Presenter>(), HomeContract.View  {

    private var rootView: View? = null
    private val datas = mutableListOf<Datas>()
    private val bannerDatas = mutableListOf<BannerResponse.Data>()
    private val homeAdapter: HomeAdapter by lazy { HomeAdapter(activity, datas) }

    private lateinit var banner: RecyclerViewBanner

    override fun createPresenter(): HomeContract.Presenter  = HomePresenter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView ?: let {
            rootView = inflater.inflate(R.layout.fragment_home, container, false)
            banner = activity?.inflater(R.layout.home_banner) as RecyclerViewBanner
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        refresh.run {
            isRefreshing = true
            setOnRefreshListener(onRefreshListener)
        }

        banner.run {
            setBannerData(bannerDatas)
        }

        home_list.run {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        homeAdapter.run {
            bindToRecyclerView(home_list)
            setOnLoadMoreListener(onLoadMoreListener, home_list)
            addHeaderView(banner)
            onItemClickListener = onItemClickListeners
        }

        mPresenter?.getBanner()
        mPresenter?.getHomeList()
    }

    private val onItemClickListeners = BaseQuickAdapter.OnItemClickListener { _, _, position ->
        if (datas.size != 0) {
            Intent(activity, WebViewAct::class.java).run {
                putExtra(Constant.CONTENT_URL_KEY, datas[position].link)
                putExtra(Constant.CONTENT_ID_KEY, datas[position].id)
                putExtra(Constant.CONTENT_TITLE_KEY, datas[position].title)
                startActivity(this)
            }
        }
    }

    private val onLoadMoreListener = BaseQuickAdapter.RequestLoadMoreListener {
        val page = homeAdapter.data.size / 20 + 1
        mPresenter?.getHomeList(page)
    }

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        refreshData()
    }

    private fun refreshData() {
        refresh.isRefreshing = true
        homeAdapter.setEnableLoadMore(false)
        banner.cancelSwitchJob()
        mPresenter?.getBanner()
        mPresenter?.getHomeList()
    }


    override fun cancelRequest() {

    }

    override fun getHomeListSuccess(result: HomeListResponse) {
        result.data.datas?.let {
            homeAdapter.run {
                val total = result.data.total

                if (result.data.offset >= total || data.size >= total) {
                    loadMoreEnd()
                    return@let
                }

                if (refresh.isRefreshing) {
                    replaceData(it)
                } else {
                    addData(it)
                }

                loadMoreComplete()
                setEnableLoadMore(true)
            }
        }
        refresh.isRefreshing = false
    }

    override fun getHomeListFailed(errorMsg: String?) {
        homeAdapter.setEnableLoadMore(false)
        homeAdapter.loadMoreFail()
        errorMsg?.let {
            activity?.toast(it)
        } ?: let {
            activity?.toast("获取数据失败！")
        }
        refresh.isRefreshing = false
    }

    override fun getBannerListSuccess(result: BannerResponse) {
        result.data?.let {
            banner_view.run {
                setBannerData(it)
            }
        }
    }

    override fun getBannerListFailed(errorMsg: String?) {
        errorMsg?.let {
            activity?.toast(it)
        }
    }
}