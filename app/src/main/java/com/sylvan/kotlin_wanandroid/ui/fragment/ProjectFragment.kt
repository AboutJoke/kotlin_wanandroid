package com.sylvan.kotlin_wanandroid.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.adapter.WeChatPagerAdapter
import com.sylvan.kotlin_wanandroid.base.BaseMvpFragment
import com.sylvan.kotlin_wanandroid.bean.WeChatUserResponse
import com.sylvan.kotlin_wanandroid.mvp.contract.ProjectContract
import com.sylvan.kotlin_wanandroid.mvp.presenter.ProjectPresenter
import kotlinx.android.synthetic.main.fragment_project.*
import kotlinx.android.synthetic.main.fragment_wechat.*
import toast

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.ui.fragment
 * @Author: sylvan
 * @Date: 19-7-25
 */
class ProjectFragment : BaseMvpFragment<ProjectContract.View, ProjectContract.Presenter>(),
    ProjectContract.View {

    private val datas = mutableListOf<WeChatUserResponse.Data>()

    private val viewpagerAdapter: WeChatPagerAdapter by lazy {
        WeChatPagerAdapter(datas, childFragmentManager)
    }

    override fun initView(view: View) {
        super.initView(view)
        project_viewpager.run {
            offscreenPageLimit = datas.size
            adapter = viewpagerAdapter
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(project_tabs))
        }
        project_tabs.run {
            setupWithViewPager(project_viewpager)
            addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(project_viewpager))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter?.getProjectNameList()
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_project

    override fun cancelRequest() {
        //
    }

    override fun createPresenter(): ProjectContract.Presenter = ProjectPresenter()

    override fun onDataSuccess(result: WeChatUserResponse) {
        result.data?.let {
            datas.addAll(result.data!!)
            viewpagerAdapter.notifyDataSetChanged()
            project_viewpager.offscreenPageLimit = datas.size
        }
    }

    override fun onDataFail(errMsg: String) {
        errMsg.let {
            activity?.toast(it)
        } ?: let {
            activity?.toast("获取数据失败！")
        }
    }
}