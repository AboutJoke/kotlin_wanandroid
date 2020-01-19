package com.sylvan.kotlin_wanandroid.mvp.model

import com.sylvan.kotlin_wanandroid.base.BaseModel
import com.sylvan.kotlin_wanandroid.mvp.contract.SystemContract
import com.sylvan.kotlin_wanandroid.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tryCatch

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.model
 * @Author: sylvan
 * @Date: 20-1-17
 */
class SystemModel : BaseModel(), SystemContract.Model {
    override fun getSystemList(presenter: SystemContract.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            tryCatch({
                it.printStackTrace()
                presenter.onDataFail(it.toString())
            }) {
                val homeList = RetrofitClient.retrofitService.getSystemList()
                presenter.onDataSuccess(homeList)
            }
        }
    }
}