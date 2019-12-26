package com.sylvan.kotlin_wanandroid.mvp.contract

import com.sylvan.kotlin_wanandroid.base.IModel
import com.sylvan.kotlin_wanandroid.base.IPresenter
import com.sylvan.kotlin_wanandroid.base.IView
import com.sylvan.kotlin_wanandroid.bean.ArticleResponse

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.contract
 * @Author: sylvan
 * @Date: 19-12-24
 */
interface RecyclerViewContract {
    interface View : IView {
        fun onDataSuccess(articles: ArticleResponse)

        fun onDataFail(errorNsg: String?)
    }

    interface Presenter : IPresenter<View> {
        fun requestKnowledgeList(page: Int, cid: Int)

        fun onDataSuccess(articles: ArticleResponse)

        fun onDataFail(errorNsg: String?)
    }

    interface Model : IModel {
        fun requestKnowledgeList(presenter: Presenter, page: Int, cid: Int)
    }
}