package com.sylvan.kotlin_wanandroid.mvp.presenter

import com.sylvan.kotlin_wanandroid.base.BasePresenter
import com.sylvan.kotlin_wanandroid.bean.SystemResponse
import com.sylvan.kotlin_wanandroid.mvp.contract.SystemContract
import com.sylvan.kotlin_wanandroid.mvp.model.SystemModel

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.presenter
 * @Author: sylvan
 * @Date: 20-1-17
 */
class SystemPresenter : BasePresenter<SystemContract.Model, SystemContract.View>(),
    SystemContract.Presenter {

    override fun createModel(): SystemContract.Model? = SystemModel()

    override fun getSystemList() {
        mModel?.getSystemList(this)
    }

    override fun onDataSuccess(result: SystemResponse) {
        mView?.onDataSuccess(result)
    }

    override fun onDataFail(errMsg: String) {
        mView?.onDataFail(errMsg)
    }
}