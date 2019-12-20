package com.sylvan.kotlin_wanandroid.ui

import Constant
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import android.widget.LinearLayout
import com.just.agentweb.AgentWeb
import com.just.agentweb.WebChromeClient
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.base.BaseActivity
import getAgentWeb
import kotlinx.android.synthetic.main.activity_webview.*


/**
 * @ClassName: com.sylvan.kotlin_wanandroid.ui
 * @Author: sylvan
 * @Date: 19-12-18
 */
class WebViewAct : BaseActivity() {

    private lateinit var mTitle: String
    private lateinit var mUrl: String
    private lateinit var webView: AgentWeb
    private var mId: Int = 0
    override fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.run {
            title = "loading..."
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        intent.extras?.let {
            mUrl = it.getString(Constant.CONTENT_URL_KEY, "")
            mId = it.getInt(Constant.CONTENT_ID_KEY)
            mTitle = it.getString(Constant.CONTENT_TITLE_KEY, "")

            webView = mUrl.getAgentWeb(
                this,
                webContent,
                layoutParams = LinearLayout.LayoutParams(-1, -1),
                webChromeClient = mWebChromeClient
            )
        }
    }

    private val mWebChromeClient: WebChromeClient =
        object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress == 100) {
                    toolbar.title = mTitle
                    webView.indicatorController.setProgress(100)
                }
            }
        }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        webView.webLifeCycle.onResume()
    }

    override fun onPause() {
        super.onPause()
        webView.webLifeCycle.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.webLifeCycle.onDestroy()
    }

    override fun initImmBar() {
        super.initImmBar()
        immersionBar.titleBar(R.id.toolbar).init()
    }

    override fun setLayoutId(): Int = R.layout.activity_webview

    override fun cancelRequest() {
    }
}