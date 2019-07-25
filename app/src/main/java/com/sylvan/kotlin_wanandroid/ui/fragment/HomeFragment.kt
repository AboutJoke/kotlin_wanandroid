package com.sylvan.kotlin_wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.base.BaseFragment
import com.sylvan.kotlin_wanandroid.bean.HomeListResponse
import com.sylvan.kotlin_wanandroid.presenter.HomeFragmentPresenterImpl
import view.HomeFragmentView

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.ui.fragment
 * @Author: sylvan
 * @Date: 19-7-25
 */
class HomeFragment : BaseFragment(), HomeFragmentView {

    private var rootView: View? = null
    private var num: String? = null

    private val homeFragmentPresenter: HomeFragmentPresenterImpl by lazy {
        HomeFragmentPresenterImpl(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView ?: let {
            rootView = inflater.inflate(R.layout.fragment_home, container, false)
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeFragmentPresenter.getHomeList()
    }


    override fun cancelRequest() {
    }

    override fun getHomeListSuccess(result: HomeListResponse) {
    }

    override fun getHomeListFailed(errorMsg: String?) {
    }
}