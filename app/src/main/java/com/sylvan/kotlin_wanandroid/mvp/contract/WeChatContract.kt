package com.sylvan.kotlin_wanandroid.mvp.contract

import com.sylvan.kotlin_wanandroid.base.IModel
import com.sylvan.kotlin_wanandroid.base.IPresenter
import com.sylvan.kotlin_wanandroid.base.IView
import com.sylvan.kotlin_wanandroid.bean.WeChatUserResponse

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.contract
 * @Author: sylvan
 * @Date: 19-12-20
 */
interface WeChatContract {

    interface View : IView {
        fun onUserNameListSuccess(result: WeChatUserResponse)

        fun onUserNameListFailed(errorNsg: String?)
    }

    interface Presenter : IPresenter<View> {
        fun gerUserNameList()

        fun getUserNameListSuccess(result: WeChatUserResponse)

        fun getUserNameListFailed(errorNsg: String?)
    }

    interface Model : IModel {
        fun getUserList(presenter: Presenter)
    }

}