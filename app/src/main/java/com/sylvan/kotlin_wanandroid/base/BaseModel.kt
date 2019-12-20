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
abstract class BaseModel: IModel, LifecycleObserver {
//    val mDisposablePool: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onDetach() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    internal fun onDestroy(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this)
    }
}