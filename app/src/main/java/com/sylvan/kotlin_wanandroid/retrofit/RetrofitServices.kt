package com.sylvan.kotlin_wanandroid.retrofit

import com.sylvan.kotlin_wanandroid.bean.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=168
     * @param page
     * @param cid
     */
    @GET("article/list/{page}/json")
    suspend
    fun getWechatArticlList(
        @Path("page") page: Int, @Query("cid") cid: Int
    ): ArticleResponse

    /**
     * 获取项目列表
     * http://wanandroid.com/wxarticle/chapters/json
     */
    @GET("project/tree/json")
    suspend fun getProjectNameList(): WeChatUserResponse

    /**
     * 广场列表数据
     * https://wanandroid.com/user_article/list/0/json
     * @param page 页码拼接在url上从0开始
     */
    @GET("user_article/list/{page}/json")
    suspend fun getSquareList(@Path("page") page: Int): ArticleResponse

    /**
     * 获取知识体系
     * http://www.wanandroid.com/tree/json
     */
    @GET("tree/json")
    suspend fun getSystemList(): SystemResponse
}