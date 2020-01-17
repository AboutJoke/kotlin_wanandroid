package com.sylvan.kotlin_wanandroid.ui.fragment

import Constant
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.adapter.SquareAdapter
import com.sylvan.kotlin_wanandroid.base.BaseMvpFragment
import com.sylvan.kotlin_wanandroid.bean.Article
import com.sylvan.kotlin_wanandroid.bean.ArticleResponse
import com.sylvan.kotlin_wanandroid.mvp.contract.SquareContract
import com.sylvan.kotlin_wanandroid.mvp.presenter.SquarePresenter
import com.sylvan.kotlin_wanandroid.ui.WebViewAct
import kotlinx.android.synthetic.main.fragment_square.*
import toast

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.ui.fragment
 * @Author: sylvan
 * @Date: 20-1-16
 */
class SquareFragment : BaseMvpFragment<SquareContract.View, SquareContract.Presenter>(),
    SquareContract.View {

    private val datas = mutableListOf<Article>()
    private val listAdapter: SquareAdapter by lazy {
        SquareAdapter(activity, datas)
    }
    private var pageSize = 20

    override fun initView(view: View) {
        super.initView(view)
        square_refresh.run {
            setOnRefreshListener(onRefreshListener)
        }
        square_list.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = listAdapter
        }
        listAdapter.run {
            setOnLoadMoreListener(onRequestLoadMoreListener, square_list)
            onItemClickListener = onItemClickListeners
        }
    }

    private val onRequestLoadMoreListener = BaseQuickAdapter.RequestLoadMoreListener {
        square_refresh.isRefreshing = false
        val page = listAdapter.data.size / (pageSize + 1)
        mPresenter?.getSquareList(page)
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

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        square_refresh.isRefreshing = true
        mPresenter?.getSquareList(0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter?.getSquareList(0)
    }

    override fun createPresenter(): SquareContract.Presenter = SquarePresenter()

    override fun attachLayoutRes(): Int = R.layout.fragment_square

    override fun cancelRequest() {
        //
    }

    override fun onDataSuccess(result: ArticleResponse) {
        result.data?.datas?.let {
            pageSize = datas.size
            listAdapter.run {

                if (square_refresh.isRefreshing) {
                    replaceData(it)
                } else {
                    addData(it)
                }

                if (result.data!!.over) {
                    loadMoreEnd()
                    return@let
                }

                loadMoreComplete()
                setEnableLoadMore(true)
                notifyDataSetChanged()
            }
        }
        square_refresh.isRefreshing = false
    }

    override fun onDataFail(errMsg: String) {
        listAdapter.setEnableLoadMore(false)
        listAdapter.loadMoreFail()
        errMsg.let {
            activity?.toast(it)
        } ?: let {
            activity?.toast("获取数据失败！")
        }
        square_refresh.isRefreshing = false
    }
}