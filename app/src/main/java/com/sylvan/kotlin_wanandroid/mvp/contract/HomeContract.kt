package com.sylvan.kotlin_wanandroid.mvp.contract

import com.sylvan.kotlin_wanandroid.base.IModel
import com.sylvan.kotlin_wanandroid.base.IPresenter
import com.sylvan.kotlin_wanandroid.base.IView
import com.sylvan.kotlin_wanandroid.bean.BannerResponse
import com.sylvan.kotlin_wanandroid.bean.HomeListResponse

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.contract
 * @Author: sylvan
 * @Date: 19-12-20
 */
interface HomeContract {

    interface View : IView {
        fun getHomeListSuccess(result: HomeListResponse)

        fun getHomeListFailed(errorMsg: String?)

        fun getBannerListSuccess(result: BannerResponse)

        fun getBannerListFailed(errorMsg: String?)
    }

    interface Presenter : IPresenter<View> {
        /**
         * home list interface
         */

        fun getHomeList(page: Int = 0)

        fun getHomeListSuccess(result: HomeListResponse)

        fun getHomeListFailed(errorNsg: String?)

        /**
         * banner list
         */

        fun getBanner()

        fun getBannerSuccess(result: BannerResponse)

        fun getBannerFailed(errorMessage: String?)
    }

    interface Model : IModel {
        fun getHomeList(homeListListener: Presenter, page: Int = 0)

        fun getBanner(onBannerListener: Presenter)
    }
}