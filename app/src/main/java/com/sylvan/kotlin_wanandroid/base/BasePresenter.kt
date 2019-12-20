package com.sylvan.kotlin_wanandroid.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.base
 * @Author: sylvan
 * @Date: 19-7-24
 */
abstract class BasePresenter<M: IModel, V: IView> : IPresenter<V>, LifecycleObserver {

    protected var mModel: M? = null
    protected var mView: V? = null

    open fun createModel(): M? = null

    override fun attachView(mView: V) {
        this.mView = mView
        mModel = createModel()
        if (mView is LifecycleOwner) {
            (mView as LifecycleOwner).lifecycle.addObserver(this)
            if (mModel != null && mModel is LifecycleObserver) {
                (mView as LifecycleOwner).lifecycle.addObserver(mModel as LifecycleObserver)
            }
        }
    }

    override fun detachView() {
        this.mModel = null
        this.mView = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        // detachView()
        owner.lifecycle.removeObserver(this)
    }

}