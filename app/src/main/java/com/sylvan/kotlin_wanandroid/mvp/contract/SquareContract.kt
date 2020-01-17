package com.sylvan.kotlin_wanandroid.mvp.contract

import com.sylvan.kotlin_wanandroid.base.IModel
import com.sylvan.kotlin_wanandroid.base.IPresenter
import com.sylvan.kotlin_wanandroid.base.IView
import com.sylvan.kotlin_wanandroid.bean.ArticleResponse

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.contract
 * @Author: sylvan
 * @Date: 20-1-16
 */
interface SquareContract {
    interface View : IView {
        fun onDataSuccess(result: ArticleResponse)

        fun onDataFail(errMsg: String)
    }

    interface Presenter : IPresenter<View> {
        fun onDataSuccess(result: ArticleResponse)

        fun onDataFail(errMsg: String)

        fun getSquareList(page: Int)
    }

    interface Model : IModel {
        fun getSquareList(presenter: Presenter, page: Int)
    }

}