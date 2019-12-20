package com.sylvan.kotlin_wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.base.BaseFragment

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.ui.fragment
 * @Author: sylvan
 * @Date: 19-7-25
 */
class ProjectFragment : BaseFragment() {

    private var rootView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView ?: let {
            rootView = inflater.inflate(R.layout.fragment_project, container, false)
        }
        return rootView
    }

    override fun initView(view: View) {
    }

    override fun cancelRequest() {
    }
}