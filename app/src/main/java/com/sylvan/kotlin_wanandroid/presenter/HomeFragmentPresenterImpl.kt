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
class HomeFragmentPresenterImpl(homeFragmentView : HomeFragmentView) : HomePresenter.onHomeListListener {

    private val homeModel: HomeModel = HomeModelImpl()

    override fun getHomeList(page: Int) {
        homeModel.getHomeList(this, page)
    }

    override fun getHomeListSuccess(result: HomeListResponse) {

    }

    override fun getHomeListFailed(errorNsg: String?) {
    }
}