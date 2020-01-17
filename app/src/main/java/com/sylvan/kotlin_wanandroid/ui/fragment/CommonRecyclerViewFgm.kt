package com.sylvan.kotlin_wanandroid.ui.fragment

import Constant
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.adapter.RecyclerViewAdapter
import com.sylvan.kotlin_wanandroid.base.BaseMvpFragment
import com.sylvan.kotlin_wanandroid.bean.Article
import com.sylvan.kotlin_wanandroid.bean.ArticleResponse
import com.sylvan.kotlin_wanandroid.mvp.contract.RecyclerViewContract
import com.sylvan.kotlin_wanandroid.mvp.presenter.RecyclerViewPresenter
import com.sylvan.kotlin_wanandroid.ui.WebViewAct
import com.sylvan.kotlin_wanandroid.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.common_article_list_layout.*
import kotlinx.android.synthetic.main.fragment_home.*
import toast

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.ui.fragment
 * @Author: sylvan
 * @Date: 19-12-24
 */
class CommonRecyclerViewFgm :
    BaseMvpFragment<RecyclerViewContract.View, RecyclerViewContract.Presenter>(),
    RecyclerViewContract.View {

    companion object {
        fun getInstance(cid: Int): CommonRecyclerViewFgm {
            val fragment = CommonRecyclerViewFgm()
            val args = Bundle()
            args.putInt(Constant.CONTENT_CID_KEY, cid)
            fragment.arguments = args
            return fragment
        }
    }

    private var cid: Int = 0
    private var pageSize = 20

    private val datas = mutableListOf<Article>()

    private val recyclerViewItemDecoration by lazy {
        activity?.let {
            SpaceItemDecoration(it)
        }
    }
    private val mAdapter: RecyclerViewAdapter by lazy {
        RecyclerViewAdapter(context, datas)
    }

    override fun createPresenter(): RecyclerViewContract.Presenter = RecyclerViewPresenter()

    override fun attachLayoutRes(): Int = R.layout.common_article_list_layout

    override fun initView(view: View) {
        super.initView(view)
        cid = arguments?.getInt(Constant.CONTENT_CID_KEY) ?: 0
        val refresh: SwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        val list: RecyclerView = view.findViewById(R.id.recyclerView)

        refresh.run {
            setOnRefreshListener(onRefreshListener)
        }
        val linearLayoutManager = LinearLayoutManager(activity)
        list.run {
            layoutManager = linearLayoutManager
            adapter = mAdapter
            recyclerViewItemDecoration?.let { addItemDecoration(it) }
        }
        mAdapter.run {
            setOnLoadMoreListener(onRequestLoadMoreListener, list)
            onItemClickListener = onItemClickListeners
        }

        mPresenter?.requestKnowledgeList(0, cid)
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
        swipeRefreshLayout.isRefreshing = true
        mPresenter?.requestKnowledgeList(0, cid)
    }

    private val onRequestLoadMoreListener = BaseQuickAdapter.RequestLoadMoreListener {
        swipeRefreshLayout.isRefreshing = false
        val page = mAdapter.data.size / (pageSize + 1)
        mPresenter?.requestKnowledgeList(page, cid)
    }

    override fun cancelRequest() {
        //
    }

    override fun onDataSuccess(articles: ArticleResponse) {
        articles.data?.datas?.let {
            pageSize = articles.data!!.size
            mAdapter.run {

                if (swipeRefreshLayout.isRefreshing) {
                    replaceData(it)
                } else {
                    addData(it)
                }

                if (articles.data!!.over) {
                    loadMoreEnd()
                    return@let
                }

                loadMoreComplete()
                setEnableLoadMore(true)
                notifyDataSetChanged()
            }
        }
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onDataFail(errorNsg: String?) {
        mAdapter.setEnableLoadMore(false)
        mAdapter.loadMoreFail()
        errorNsg?.let {
            activity?.toast(it)
        } ?: let {
            activity?.toast("获取数据失败！")
        }
        refresh?.isRefreshing = false
    }
}