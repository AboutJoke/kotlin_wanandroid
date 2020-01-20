package com.sylvan.kotlin_wanandroid.bean

import java.io.Serializable

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.bean
 * @Author: sylvan
 * @Date: 20-1-17
 */
data class SystemResponse(
    var errorCode: Int,
    var errorMsg: String?,
    var data: List<Data>?
) {
    data class Data(
        val children: MutableList<Knowledge>,
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Int,
        val parentChapterId: Int,
        val visible: Int
    ) : Serializable

    data class Knowledge(
        val children: List<Any>,
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Int,
        val parentChapterId: Int,
        val visible: Int
    ) : Serializable
}