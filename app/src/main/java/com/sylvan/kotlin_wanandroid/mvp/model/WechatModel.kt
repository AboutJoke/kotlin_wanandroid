package com.sylvan.kotlin_wanandroid.mvp.model

import com.sylvan.kotlin_wanandroid.base.BaseModel
import com.sylvan.kotlin_wanandroid.mvp.contract.WeChatContract
import com.sylvan.kotlin_wanandroid.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tryCatch

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.model
 * @Author: sylvan
 * @Date: 19-12-19
 */
class WechatModel : BaseModel(), WeChatContract.Model {
    override fun getUserList(presenter: WeChatContract.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            tryCatch({
                it.printStackTrace()
                presenter.getUserNameListFailed(it.toString())
            }) {
                val nameList = RetrofitClient.retrofitService.getWeChatNameList()
                presenter.getUserNameListSuccess(nameList)
            }
        }
    }
}