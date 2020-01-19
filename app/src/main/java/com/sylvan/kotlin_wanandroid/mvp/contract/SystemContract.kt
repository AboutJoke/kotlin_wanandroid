package com.sylvan.kotlin_wanandroid.mvp.contract

import com.sylvan.kotlin_wanandroid.base.IModel
import com.sylvan.kotlin_wanandroid.base.IPresenter
import com.sylvan.kotlin_wanandroid.base.IView
import com.sylvan.kotlin_wanandroid.bean.SystemResponse

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.contract
 * @Author: sylvan
 * @Date: 20-1-17
 */
interface SystemContract {

    interface View: IView {
        fun onDataSuccess(result: SystemResponse)

        fun onDataFail(errMsg: String)
    }

    interface Presenter: IPresenter<View> {
        fun getSystemList()

        fun onDataSuccess(result: SystemResponse)

        fun onDataFail(errMsg: String)
    }

    interface Model: IModel {
        fun getSystemList(presenter: Presenter)
    }
}