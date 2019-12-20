package com.sylvan.kotlin_wanandroid.mvp.presenter

import com.sylvan.kotlin_wanandroid.base.BasePresenter
import com.sylvan.kotlin_wanandroid.bean.BannerResponse
import com.sylvan.kotlin_wanandroid.bean.HomeListResponse
import com.sylvan.kotlin_wanandroid.mvp.contract.HomeContract
import com.sylvan.kotlin_wanandroid.mvp.model.HomeModel


/**
 * @ClassName: com.sylvan.kotlin_wanandroid.base
 * @Author: sylvan
 * @Date: 19-7-24
 */
class HomePresenter : BasePresenter<HomeContract.Model, HomeContract.View>(),
    HomeContract.Presenter {

    override fun createModel(): HomeContract.Model? =
        HomeModel()

    override fun getHomeList(page: Int) {
        mModel?.getHomeList(this, page)
    }

    override fun getHomeListSuccess(result: HomeListResponse) {
        if (result.errorCode != 0) {
            mView?.getHomeListFailed(result.errorMsg)
            return
        }
        mView?.getHomeListSuccess(result)
    }

    override fun getHomeListFailed(errorNsg: String?) {
    }

    override fun getBanner() {
        mModel?.getBanner(this)
    }

    override fun getBannerSuccess(result: BannerResponse) {
        if (result.errorCode != 0) {
            mView?.getBannerListFailed(result.errorMsg)
            return
        }
        mView?.getBannerListSuccess(result)
    }

    override fun getBannerFailed(errorMessage: String?) {
    }


}
