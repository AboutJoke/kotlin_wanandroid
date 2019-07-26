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
import com.sylvan.kotlin_wanandroid.bean.Datas
import com.sylvan.kotlin_wanandroid.bean.HomeListResponse
import com.sylvan.kotlin_wanandroid.presenter.HomeFragmentPresenterImpl
import kotlinx.android.synthetic.main.fragment_home.*
import view.HomeFragmentView

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.ui.fragment
 * @Author: sylvan
 * @Date: 19-7-25
 */
class HomeFragment : BaseFragment(), HomeFragmentView {

    private var rootView: View? = null
    private val datas = mutableListOf<Datas>()
    private val adapter: HomeAdapter by lazy { HomeAdapter(activity, datas) }

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
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        refresh.run {
            isRefreshing = true
            setOnRefreshListener(onRefreshListener)
        }

        home_list.run {
            adapter = adapter
            layoutManager = LinearLayoutManager(activity)
        }

        adapter.run {
            bindToRecyclerView(home_list)
            setOnLoadMoreListener(onLoadMoreListener, home_list)
        }

        homeFragmentPresenter.getHomeList()
    }

    private val onLoadMoreListener = BaseQuickAdapter.RequestLoadMoreListener {

    }

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {

    }


    override fun cancelRequest() {
        homeFragmentPresenter.cancelRequest()
    }

    override fun getHomeListSuccess(result: HomeListResponse) {
        result.data.datas?.let {
            adapter.run {
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
    }
}