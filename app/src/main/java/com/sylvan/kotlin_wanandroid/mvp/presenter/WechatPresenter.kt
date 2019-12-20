package com.sylvan.kotlin_wanandroid.mvp.presenter

import com.sylvan.kotlin_wanandroid.base.BasePresenter
import com.sylvan.kotlin_wanandroid.mvp.contract.WeChatContract
import com.sylvan.kotlin_wanandroid.mvp.model.WechatModel

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.presenter
 * @Author: sylvan
 * @Date: 19-12-19
 */
class WechatPresenter: BasePresenter<WeChatContract.Model, WeChatContract.View>(), WeChatContract.Presenter {

    override fun createModel(): WeChatContract.Model? {
        return WechatModel()
    }


    override fun gerUserNameList() {
    }

    override fun getUserArticleList(id: String, page: Int) {
    }

}