package com.sylvan.kotlin_wanandroid.retrofit

import com.sylvan.kotlin_wanandroid.bean.BannerResponse
import com.sylvan.kotlin_wanandroid.bean.HomeListResponse
import com.sylvan.kotlin_wanandroid.bean.WeChatUserResponse
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

    /**
     * 首页Banner
     * @return BannerResponse
     */
    @GET("/banner/json")
    suspend fun getBanner(): BannerResponse

    /**
     * 获取公众号列表
     * http://wanandroid.com/wxarticle/chapters/json
     */
    @GET("/wxarticle/chapters/json")
    suspend fun getWeChatNameList(): WeChatUserResponse

    /**
     * 查看某个公众号历史数据
     * http://wanandroid.com/wxarticle/list/405/1/json
     * @param id 公众号 ID
     * @param page 公众号页码
     */
//    @GET("/wxarticle/list/{id}/{page}/json")
//    suspend
//    fun getWechatArticlList(
//        @Path("id") id: String,
//        @Path("page") page: Int
//    ):
}