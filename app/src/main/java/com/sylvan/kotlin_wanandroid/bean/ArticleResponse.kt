package com.sylvan.kotlin_wanandroid.bean

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.bean
 * @Author: sylvan
 * @Date: 19-12-24
 */
data class ArticleResponse(
    var errorCode: Int,
    var errorMsg: String?,
    var data: Data?
) {
    data class Data(
        val curPage: Int,
        var datas: MutableList<Article>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
    )
}