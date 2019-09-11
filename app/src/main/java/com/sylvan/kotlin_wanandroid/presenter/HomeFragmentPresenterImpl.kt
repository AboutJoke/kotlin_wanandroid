package com.sylvan.kotlin_wanandroid.presenter

import com.sylvan.kotlin_wanandroid.bean.BannerResponse
import com.sylvan.kotlin_wanandroid.bean.HomeListResponse
import com.sylvan.kotlin_wanandroid.model.HomeModel
import com.sylvan.kotlin_wanandroid.model.HomeModelImpl
import view.HomeFragmentView

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.presenter
 * @Author: sylvan
 * @Date: 19-7-24
 */
class HomeFragmentPresenterImpl(private val homeFragmentView: HomeFragmentView) : HomePresenter.onHomeListListener,
    HomePresenter.OnBannerListener {

    private val homeModel: HomeModel = HomeModelImpl()

    override fun getHomeList(page: Int) {
        homeModel.getHomeList(this, page)
    }

    override fun getHomeListSuccess(result: HomeListResponse) {
        if (result.errorCode != 0) {
            homeFragmentView.getHomeListFailed(result.errorMsg)
            return
        }
        homeFragmentView.getHomeListSuccess(result)
    }

    override fun getHomeListFailed(errorNsg: String?) {
    }

    override fun getBanner() {
        homeModel.getBanner(this)
    }

    override fun getBannerSuccess(result: BannerResponse) {
        if (result.errorCode != 0) {
            homeFragmentView.getBannerListFailed(result.errorMsg)
            return
        }
        homeFragmentView.getBannerListSuccess(result)
    }

    override fun getBannerFailed(errorMessage: String?) {
    }

    fun cancelRequest() {
    }
}