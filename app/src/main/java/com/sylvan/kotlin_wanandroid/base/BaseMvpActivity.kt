package com.sylvan.kotlin_wanandroid.base

import toast

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.base
 * @Author: sylvan
 * @Date: 19-12-20
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseMvpActivity<in V : IView, P : IPresenter<V>> : BaseActivity(), IView {

    /**
     * Presenter
     */
    protected var mPresenter: P? = null

    protected abstract fun createPresenter(): P

    override fun initView() {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        this.mPresenter = null
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showError(errorMsg: String) {
        toast(errorMsg)
    }

    override fun showDefaultMsg(msg: String) {
        toast(msg)
    }

    override fun showMsg(msg: String) {
        toast(msg)
    }


}