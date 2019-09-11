package com.sylvan.kotlin_wanandroid.presenter

import com.sylvan.kotlin_wanandroid.bean.BannerResponse
import com.sylvan.kotlin_wanandroid.bean.HomeListResponse


/**
 * @ClassName: com.sylvan.kotlin_wanandroid.base
 * @Author: sylvan
 * @Date: 19-7-24
 */
interface HomePresenter {

    /**
     * home list interface
     */
    interface onHomeListListener {

        fun getHomeList(page : Int = 0)

        fun getHomeListSuccess(result : HomeListResponse)

        fun getHomeListFailed(errorNsg : String?)
    }

    /**
     * banner list
     */
    interface OnBannerListener {

        fun getBanner()

        fun getBannerSuccess(result: BannerResponse)

        fun getBannerFailed(errorMessage: String?)
    }
}
