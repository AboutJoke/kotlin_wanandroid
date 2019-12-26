package com.sylvan.kotlin_wanandroid.mvp.presenter

import com.sylvan.kotlin_wanandroid.base.BasePresenter
import com.sylvan.kotlin_wanandroid.bean.ArticleResponse
import com.sylvan.kotlin_wanandroid.mvp.contract.RecyclerViewContract
import com.sylvan.kotlin_wanandroid.mvp.model.RecyclerViewModel

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.presenter
 * @Author: sylvan
 * @Date: 19-12-24
 */
class RecyclerViewPresenter : BasePresenter<RecyclerViewContract.Model, RecyclerViewContract.View>(), RecyclerViewContract.Presenter{
    override fun createModel(): RecyclerViewContract.Model? = RecyclerViewModel()

    override fun requestKnowledgeList(page: Int, cid: Int) {
        mModel?.requestKnowledgeList(this, page, cid)
    }

    override fun onDataSuccess(articles: ArticleResponse) {
        mView?.onDataSuccess(articles)
    }

    override fun onDataFail(errorNsg: String?) {
        mView?.onDataFail(errorNsg)
    }
}