package com.sylvan.kotlin_wanandroid.model

import com.sylvan.kotlin_wanandroid.presenter.HomePresenter

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.model
 * @Author: sylvan
 * @Date: 19-7-24
 */
interface HomeModel {

    fun getHomeList(homeListListener: HomePresenter.onHomeListListener, page: Int = 0)

    fun getBanner(onBannerListener: HomePresenter.OnBannerListener)

}