package com.sylvan.kotlin_wanandroid.bean

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.bean
 * @Author: sylvan
 * @Date: 19-12-19
 */

data class WeChatUserResponse(
    var errorCode: Int,
    var errorMsg: String?,
    var data: List<Data>?
) {
    data class Data(
        var id: Int,
        var url: String,
        var imagePath: String,
        var title: String,
        var desc: String,
        var isVisible: Int,
        var order: Int,
        var `type`: Int
    )
}
