package com.sylvan.kotlin_wanandroid.base

import androidx.fragment.app.Fragment

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.base
 * @Author: sylvan
 * @Date: 19-7-24
 */
abstract class BaseFragment : Fragment() {

    protected var isFirst: Boolean = true

    /**
     * cancel request
     */
    protected abstract fun cancelRequest()

    override fun onDestroyView() {
        super.onDestroyView()
        cancelRequest()
    }
}