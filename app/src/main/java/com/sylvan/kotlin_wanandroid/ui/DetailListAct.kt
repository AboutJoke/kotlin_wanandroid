package com.sylvan.kotlin_wanandroid.ui

import Constant
import com.google.android.material.tabs.TabLayout
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.adapter.SystemDetailAdapter
import com.sylvan.kotlin_wanandroid.base.BaseActivity
import com.sylvan.kotlin_wanandroid.bean.SystemResponse
import kotlinx.android.synthetic.main.activity_detail_list.*

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.ui
 * @Author: sylvan
 * @Date: 20-1-20
 */
class DetailListAct : BaseActivity() {
    private val data = mutableListOf<SystemResponse.Knowledge>()

    private val systemAdapter: SystemDetailAdapter by lazy {
        SystemDetailAdapter(data, supportFragmentManager)
    }
    private lateinit var toolbarTitle: String

    override fun initView() {
        intent.extras?.let {
            toolbarTitle = it.getString(Constant.CONTENT_TITLE_KEY) ?: ""
            it.getSerializable(Constant.CONTENT_DATA_KEY)?.let {
                val response = it as SystemResponse.Data
                response.children?.let { children ->
                    data.addAll(children)
                    systemAdapter.notifyDataSetChanged()
                }
            }
        }

        detail_viewpager.run {
            adapter = systemAdapter
            offscreenPageLimit = data.size
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(detail_tabs))
        }
        detail_tabs.run {
            setupWithViewPager(detail_viewpager)
            addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(detail_viewpager))
        }
        detail_toolbar.run {
            title = toolbarTitle
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun initImmBar() {
        super.initImmBar()
        immersionBar.titleBar(detail_toolbar).init()
    }

    override fun setLayoutId(): Int = R.layout.activity_detail_list

    override fun cancelRequest() {
        //
    }
}