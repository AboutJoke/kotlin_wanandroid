package com.sylvan.kotlin_wanandroid.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.adapter.SystemAdapter
import com.sylvan.kotlin_wanandroid.base.BaseMvpFragment
import com.sylvan.kotlin_wanandroid.bean.SystemResponse
import com.sylvan.kotlin_wanandroid.mvp.contract.SystemContract
import com.sylvan.kotlin_wanandroid.mvp.presenter.SystemPresenter
import kotlinx.android.synthetic.main.fragment_system.*
import toast

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.ui.fragment
 * @Author: sylvan
 * @Date: 19-7-25
 */
class SystemFragment : BaseMvpFragment<SystemContract.View, SystemContract.Presenter>(), SystemContract.View {

    companion object {
        fun getInstance(): SystemFragment = SystemFragment()
    }
    private val datas = mutableListOf<SystemResponse.Data>()
    private val listAdapter : SystemAdapter by lazy {
        SystemAdapter(activity, datas = datas)
    }

    override fun initView(view: View) {
        super.initView(view)
        system_refresh.run {
            setOnRefreshListener(onRefreshListener)
        }
        system_list.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = listAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter?.getSystemList()
    }

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        system_refresh.isRefreshing = true
        mPresenter?.getSystemList()
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_system

    override fun cancelRequest() {
        //
    }

    override fun createPresenter(): SystemContract.Presenter = SystemPresenter()

    override fun onDataSuccess(result: SystemResponse) {
        result.data?.let {
            listAdapter.run {
                if (system_refresh.isRefreshing) {
                    replaceData(it)
                } else {
                    addData(it)
                }

                loadMoreComplete()
                setEnableLoadMore(true)
                notifyDataSetChanged()
            }
        }
        system_refresh?.isRefreshing = false
    }

    override fun onDataFail(errMsg: String) {
        system_refresh?.isRefreshing = false
        errMsg?.let {
            activity?.toast(it)
        } ?: let {
            activity?.toast("获取数据失败！")
        }
    }
}