package com.sylvan.kotlin_wanandroid.retrofit

import com.sylvan.kotlin_wanandroid.bean.HomeListResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.retrofit
 * @Author: sylvan
 * @Date: 19-7-25
 */
interface RetrofitServices {
    /**
     * 首页数据
     * http://www.wanandroid.com/article/list/0/json
     * @param page page
     */

    @GET("/article/list/{page}/json")
    suspend fun getHomeList(
        @Path("page") page: Int
    ): HomeListResponse
}