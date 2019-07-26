package com.sylvan.kotlin_wanandroid.model

import com.sylvan.kotlin_wanandroid.presenter.HomePresenter
import com.sylvan.kotlin_wanandroid.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tryCatch

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.model
 * @Author: sylvan
 * @Date: 19-7-24
 */
class HomeModelImpl : HomeModel {

    override fun getHomeList(homeListListener: HomePresenter.onHomeListListener, page: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            tryCatch({
                it.printStackTrace()
                homeListListener.getHomeListFailed(it.toString())
            }) {
                val homeList = RetrofitClient.retrofitService.getHomeList(page)
                homeListListener.getHomeListSuccess(homeList)
            }
        }
    }
}