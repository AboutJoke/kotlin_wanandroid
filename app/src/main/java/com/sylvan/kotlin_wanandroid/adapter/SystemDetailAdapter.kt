package com.sylvan.kotlin_wanandroid.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.sylvan.kotlin_wanandroid.bean.SystemResponse
import com.sylvan.kotlin_wanandroid.ui.fragment.CommonRecyclerViewFgm

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.adapter
 * @Author: sylvan
 * @Date: 20-1-20
 */
class SystemDetailAdapter(
    private val list: MutableList<SystemResponse.Knowledge>,
    fm: FragmentManager?
) : FragmentPagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragments = mutableListOf<Fragment>()

    init {
        fragments.clear()
        list.forEach {
            fragments.add(CommonRecyclerViewFgm.getInstance(it.id))
        }
    }

    override fun getItem(position: Int): Fragment =
        CommonRecyclerViewFgm.getInstance(list[position].id)

    override fun getCount(): Int {
        return list.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list[position].name
    }

    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE


}