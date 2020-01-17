package com.sylvan.kotlin_wanandroid.mvp.presenter

import com.sylvan.kotlin_wanandroid.base.BasePresenter
import com.sylvan.kotlin_wanandroid.bean.ArticleResponse
import com.sylvan.kotlin_wanandroid.mvp.contract.SquareContract
import com.sylvan.kotlin_wanandroid.mvp.model.SquareModel

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.presenter
 * @Author: sylvan
 * @Date: 20-1-16
 */
class SquarePresenter : BasePresenter<SquareContract.Model, SquareContract.View>(),
    SquareContract.Presenter {

    override fun createModel(): SquareContract.Model? = SquareModel()

    override fun onDataSuccess(result: ArticleResponse) {
        mView?.onDataSuccess(result)
    }

    override fun onDataFail(errMsg: String) {
        mView?.onDataFail(errMsg)
    }

    override fun getSquareList(page: Int) {
        mModel?.getSquareList(this, page)
    }
}