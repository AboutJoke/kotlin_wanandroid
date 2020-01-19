package com.sylvan.kotlin_wanandroid.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.adapter.WeChatPagerAdapter
import com.sylvan.kotlin_wanandroid.base.BaseMvpFragment
import com.sylvan.kotlin_wanandroid.bean.WeChatUserResponse
import com.sylvan.kotlin_wanandroid.mvp.contract.WeChatContract
import com.sylvan.kotlin_wanandroid.mvp.presenter.WechatPresenter
import kotlinx.android.synthetic.main.fragment_wechat.*
import toast

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.ui.fragment
 * @Author: sylvan
 * @Date: 19-7-25
 */
class WeChatFragment : BaseMvpFragment<WeChatContract.View, WeChatContract.Presenter>(),
    WeChatContract.View {

    private val datas = mutableListOf<WeChatUserResponse.Data>()

    companion object {
        fun getInstance(): WeChatFragment = WeChatFragment()
    }

    private val viewPagerAdapter: WeChatPagerAdapter by lazy {
        WeChatPagerAdapter(datas, childFragmentManager)
    }

    override fun createPresenter(): WeChatContract.Presenter {
        return WechatPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter?.gerUserNameList()
    }

    override fun initView(view: View) {
        super.initView(view)
        val viewPager: ViewPager = view.findViewById(R.id.wechat_viewpager)
        val tabLayout: TabLayout = view.findViewById(R.id.wechat_tabs)
        viewPager.run {
            adapter = viewPagerAdapter
            offscreenPageLimit = datas.size
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        }

        tabLayout.run {
            setupWithViewPager(viewPager)
            addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))
            addOnTabSelectedListener(onTabSelectedListener)
        }
    }

    private val onTabSelectedListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(p0: TabLayout.Tab?) {

        }

        override fun onTabUnselected(p0: TabLayout.Tab?) {
        }

        override fun onTabSelected(p0: TabLayout.Tab?) {
        }

    }

    override fun attachLayoutRes(): Int = R.layout.fragment_wechat

    override fun cancelRequest() {
    }

    override fun onUserNameListSuccess(result: WeChatUserResponse) {
        result.data?.let {
            datas.addAll(result.data!!)
            viewPagerAdapter.notifyDataSetChanged()
            wechat_viewpager.offscreenPageLimit = datas.size
        }
    }

    override fun onUserNameListFailed(errorNsg: String?) {
        errorNsg?.let {
            activity?.toast(it)
        } ?: let {
            activity?.toast("获取数据失败！")
        }
    }
}