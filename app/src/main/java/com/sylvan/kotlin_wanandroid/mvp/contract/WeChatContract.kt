package com.sylvan.kotlin_wanandroid.mvp.contract

import com.sylvan.kotlin_wanandroid.base.IModel
import com.sylvan.kotlin_wanandroid.base.IPresenter
import com.sylvan.kotlin_wanandroid.base.IView

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.contract
 * @Author: sylvan
 * @Date: 19-12-20
 */
interface WeChatContract {

    interface View : IView {
        fun show()
    }

    interface Presenter : IPresenter<View> {
        fun gerUserNameList()

        fun getUserArticleList(id: String, page : Int = 0)
    }

    interface Model : IModel {
        fun getUserList()

        fun getUserArticleList(id: String, page : Int = 0)
    }

}