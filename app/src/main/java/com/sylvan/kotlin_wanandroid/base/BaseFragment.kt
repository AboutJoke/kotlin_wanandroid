package com.sylvan.kotlin_wanandroid.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.base
 * @Author: sylvan
 * @Date: 19-7-24
 */
abstract class BaseFragment : Fragment() {

    protected var isFirst: Boolean = true

    /**
     * 初始化 View
     */
    abstract fun initView(view: View)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    /**
     * cancel request
     */
    protected abstract fun cancelRequest()

    override fun onDestroyView() {
        super.onDestroyView()
        cancelRequest()
    }
}