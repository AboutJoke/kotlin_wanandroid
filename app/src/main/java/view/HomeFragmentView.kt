package view

import com.sylvan.kotlin_wanandroid.bean.HomeListResponse

/**
 * @ClassName: view
 * @Author: sylvan
 * @Date: 19-7-24
 */
interface HomeFragmentView {

    fun getHomeListSuccess(result: HomeListResponse)

    fun getHomeListFailed(errorMsg: String?)
}