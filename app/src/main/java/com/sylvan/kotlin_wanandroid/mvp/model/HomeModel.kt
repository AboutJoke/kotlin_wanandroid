package com.sylvan.kotlin_wanandroid.mvp.model

import com.sylvan.kotlin_wanandroid.base.BaseModel
import com.sylvan.kotlin_wanandroid.mvp.contract.HomeContract
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
class HomeModel: BaseModel(), HomeContract.Model {
    override fun getHomeList(homeListListener: HomeContract.Presenter, page: Int) {
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

    override fun getBanner(onBannerListener: HomeContract.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            tryCatch({
                it.printStackTrace()
                onBannerListener.getBannerFailed(it.toString())
            }) {
                val banner = RetrofitClient.retrofitService.getBanner()
                onBannerListener.getBannerSuccess(banner)
            }
        }
    }

}