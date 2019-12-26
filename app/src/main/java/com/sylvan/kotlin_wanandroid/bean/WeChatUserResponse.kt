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
        var name: String,
        var courseId: Int,
        var order: Int,
        var parentChapterId: Int,
        var userControlSetTop: Boolean,
        var visible: Int
    )
}