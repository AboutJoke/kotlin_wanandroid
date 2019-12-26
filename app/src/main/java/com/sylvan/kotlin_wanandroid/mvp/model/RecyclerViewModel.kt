package com.sylvan.kotlin_wanandroid.mvp.model

import com.sylvan.kotlin_wanandroid.base.BaseModel
import com.sylvan.kotlin_wanandroid.mvp.contract.RecyclerViewContract
import com.sylvan.kotlin_wanandroid.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tryCatch

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.mvp.model
 * @Author: sylvan
 * @Date: 19-12-24
 */
class RecyclerViewModel : BaseModel(), RecyclerViewContract.Model{
    override fun requestKnowledgeList(
        presenter: RecyclerViewContract.Presenter,
        page: Int,
        cid: Int
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            tryCatch({
                it.printStackTrace()
                presenter.onDataFail(it.toString())
            }) {
                val homeList = RetrofitClient.retrofitService.getWechatArticlList(page, cid)
                presenter.onDataSuccess(homeList)
            }
        }
    }
}