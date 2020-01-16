package com.sylvan.kotlin_wanandroid.mvp.presenter

import com.sylvan.kotlin_wanandroid.base.BasePresenter
import com.sylvan.kotlin_wanandroid.bean.WeChatUserResponse
import com.sylvan.kotlin_wanandroid.mvp.contract.ProjectContract
import com.sylvan.kotlin_wanandroid.mvp.model.ProjectModel

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.presenter
 * @Author: sylvan
 * @Date: 20-1-15
 */
class ProjectPresenter :BasePresenter<ProjectContract.Model, ProjectContract.View>(), ProjectContract.Presenter {
    override fun createModel(): ProjectContract.Model? {
        return ProjectModel()
    }

    override fun getProjectNameList() {
        mModel?.getProjectNameList(this)
    }

    override fun onDataSuccess(result: WeChatUserResponse) {
        mView?.onDataSuccess(result)
    }

    override fun onDataFail(errMsg: String) {
        mView?.onDataFail(errMsg)
    }
}