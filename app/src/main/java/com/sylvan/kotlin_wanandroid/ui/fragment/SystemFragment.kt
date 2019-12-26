package com.sylvan.kotlin_wanandroid.ui.fragment

import android.view.View
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.base.BaseFragment

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.ui.fragment
 * @Author: sylvan
 * @Date: 19-7-25
 */
class SystemFragment : BaseFragment() {

    companion object {
        fun getInstance(): SystemFragment = SystemFragment()
    }

    override fun initView(view: View) {
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_system

    override fun cancelRequest() {
    }
}