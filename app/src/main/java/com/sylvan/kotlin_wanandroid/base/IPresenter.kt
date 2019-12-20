package com.sylvan.kotlin_wanandroid.base

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.model
 * @Author: sylvan
 * @Date: 19-12-19
 */
interface IPresenter<in V : IView> {

    /**
     * 绑定 View
     */
    fun attachView(mView: V)

    /**
     * 解绑 View
     */
    fun detachView()

}