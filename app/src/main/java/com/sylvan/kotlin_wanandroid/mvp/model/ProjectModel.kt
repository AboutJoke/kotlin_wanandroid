package com.sylvan.kotlin_wanandroid.mvp.model

import com.sylvan.kotlin_wanandroid.base.BaseModel
import com.sylvan.kotlin_wanandroid.mvp.contract.ProjectContract
import com.sylvan.kotlin_wanandroid.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tryCatch

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.model
 * @Author: sylvan
 * @Date: 20-1-15
 */
class ProjectModel : BaseModel(), ProjectContract.Model {
    override fun getProjectNameList(presenter: ProjectContract.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            tryCatch({
                it.printStackTrace()
                presenter.onDataFail(it.toString())
            }) {
                val nameList = RetrofitClient.retrofitService.getProjectNameList()
                presenter.onDataSuccess(nameList)
            }
        }
    }
}