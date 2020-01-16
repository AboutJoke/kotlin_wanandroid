package com.sylvan.kotlin_wanandroid.mvp.contract

import com.sylvan.kotlin_wanandroid.base.IModel
import com.sylvan.kotlin_wanandroid.base.IPresenter
import com.sylvan.kotlin_wanandroid.base.IView
import com.sylvan.kotlin_wanandroid.bean.WeChatUserResponse

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.contract
 * @Author: sylvan
 * @Date: 20-1-15
 */
interface ProjectContract {

    interface View : IView {
        fun onDataSuccess(result: WeChatUserResponse)
        fun onDataFail(errMsg: String)
    }

    interface Presenter : IPresenter<View> {
        fun getProjectNameList()

        fun onDataSuccess(result: WeChatUserResponse)

        fun onDataFail(errMsg: String)
    }

    interface Model : IModel {
        fun getProjectNameList(presenter: Presenter)
    }

}