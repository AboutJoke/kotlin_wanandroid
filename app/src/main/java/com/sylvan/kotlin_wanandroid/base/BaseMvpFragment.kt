package com.sylvan.kotlin_wanandroid.base

import android.view.View
import toast

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.base
 * @Author: sylvan
 * @Date: 19-12-20
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseMvpFragment<in V : IView, P : IPresenter<V>> : BaseFragment(), IView {

    /**
     * Presenter
     */
    protected var mPresenter: P? = null

    protected abstract fun createPresenter(): P

    override fun initView(view: View) {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPresenter?.detachView()
        this.mPresenter = null
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(errorMsg: String) {
        activity?.toast(errorMsg)
    }

    override fun showDefaultMsg(msg: String) {
        activity?.toast(msg)
    }

    override fun showMsg(msg: String) {
        activity?.toast(msg)
    }

}