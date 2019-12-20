package com.sylvan.kotlin_wanandroid.mvp.model

import com.sylvan.kotlin_wanandroid.base.BaseModel
import com.sylvan.kotlin_wanandroid.mvp.contract.WeChatContract

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.model
 * @Author: sylvan
 * @Date: 19-12-19
 */
class WechatModel:BaseModel(), WeChatContract.Model {
    override fun getUserList() {
    }

    override fun getUserArticleList(id: String, page: Int) {
    }

}