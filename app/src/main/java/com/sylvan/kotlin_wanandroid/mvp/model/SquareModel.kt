package com.sylvan.kotlin_wanandroid.mvp.model

import com.sylvan.kotlin_wanandroid.base.BaseModel
import com.sylvan.kotlin_wanandroid.mvp.contract.SquareContract
import com.sylvan.kotlin_wanandroid.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tryCatch

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.model
 * @Author: sylvan
 * @Date: 20-1-16
 */
class SquareModel : BaseModel(), SquareContract.Model {
    override fun getSquareList(presenter: SquareContract.Presenter, page: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            tryCatch({
                it.printStackTrace()
                presenter.onDataFail(it.toString())
            }) {
                val homeList = RetrofitClient.retrofitService.getSquareList(page)
                presenter.onDataSuccess(homeList)
            }
        }
    }
}