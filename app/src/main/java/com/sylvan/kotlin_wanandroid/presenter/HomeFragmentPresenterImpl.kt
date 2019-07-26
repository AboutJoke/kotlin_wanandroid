package com.sylvan.kotlin_wanandroid.presenter

import com.sylvan.kotlin_wanandroid.bean.HomeListResponse
import com.sylvan.kotlin_wanandroid.model.HomeModel
import com.sylvan.kotlin_wanandroid.model.HomeModelImpl
import view.HomeFragmentView

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.presenter
 * @Author: sylvan
 * @Date: 19-7-24
 */
class HomeFragmentPresenterImpl(private val homeFragmentView : HomeFragmentView) : HomePresenter.onHomeListListener {

    private val homeModel: HomeModel = HomeModelImpl()

    override fun getHomeList(page: Int) {
        homeModel.getHomeList(this, page)
    }

    override fun getHomeListSuccess(result: HomeListResponse) {
        if (result.errorCode != 0) {
            homeFragmentView.getHomeListFailed(result.errorMsg)
            return
        }
//        // 列表总数
//        val total = result.data.total
//        if (total == 0) {
//            homeFragmentView.getHomeListZero()
//            return
//        }
//        // 当第一页小于一页总数时
//        if (total < result.data.size) {
//            homeFragmentView.getHomeListSmall(result)
//            return
//        }
        homeFragmentView.getHomeListSuccess(result)
    }

    override fun getHomeListFailed(errorNsg: String?) {
    }

    fun cancelRequest() {
//        homeModel.cancelBannerRequest()
//        homeModel.cancelHomeListRequest()
//        collectArticleModel.cancelCollectRequest()
    }
}