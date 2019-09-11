package com.sylvan.kotlin_wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.adapter.HomeAdapter
import com.sylvan.kotlin_wanandroid.base.BaseFragment
import com.sylvan.kotlin_wanandroid.bean.BannerResponse
import com.sylvan.kotlin_wanandroid.bean.Datas
import com.sylvan.kotlin_wanandroid.bean.HomeListResponse
import com.sylvan.kotlin_wanandroid.presenter.HomeFragmentPresenterImpl
import com.sylvan.kotlin_wanandroid.widget.RecyclerViewBanner
import inflater
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_banner.*
import toast
import view.HomeFragmentView

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.ui.fragment
 * @Author: sylvan
 * @Date: 19-7-25
 */
class HomeFragment : BaseFragment(), HomeFragmentView {

    private var rootView: View? = null
    private val datas = mutableListOf<Datas>()
    private val bannerDatas = mutableListOf<BannerResponse.Data>()
    private val homeAdapter: HomeAdapter by lazy { HomeAdapter(activity, datas) }

    private lateinit var banner: RecyclerViewBanner

    private val homeFragmentPresenter: HomeFragmentPresenterImpl by lazy {
        HomeFragmentPresenterImpl(this)
    }

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
        }

        homeFragmentPresenter.getBanner()
        homeFragmentPresenter.getHomeList()
    }

    private val onLoadMoreListener = BaseQuickAdapter.RequestLoadMoreListener {
        val page = homeAdapter.data.size / 20 + 1
        homeFragmentPresenter.getHomeList(page)
    }

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        refreshData()
    }

    private fun refreshData() {
        refresh.isRefreshing = true
        homeAdapter.setEnableLoadMore(false)
        banner.cancelSwitchJob()
        homeFragmentPresenter.getBanner()
        homeFragmentPresenter.getHomeList()
    }


    override fun cancelRequest() {
        homeFragmentPresenter.cancelRequest()
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