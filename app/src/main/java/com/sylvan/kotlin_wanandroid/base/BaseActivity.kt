package com.sylvan.kotlin_wanandroid.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar

/**
 * @ClassName: com.sylvan.kotlin_wanandroid
 * @Author: sylvan
 * @Date: 19-6-27
 */
abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var immersionBar: ImmersionBar

    private val imm : InputMethodManager by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        initImmBar()
    }

    protected open fun initImmBar() {
        immersionBar = ImmersionBar.with(this)
        immersionBar.init()
    }

    protected abstract fun setLayoutId(): Int

    override fun onDestroy() {
        super.onDestroy()
        immersionBar.destroy()
        cancelRequest()
    }

    protected abstract fun cancelRequest()

    override fun finish() {
        if (!isFinishing) {
            super.finish()
            hideSoftKeyBoard()
        }
    }

    private fun hideSoftKeyBoard() {
        currentFocus?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 2)
        }
    }

}

