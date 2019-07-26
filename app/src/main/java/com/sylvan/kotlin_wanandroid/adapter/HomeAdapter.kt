package com.sylvan.kotlin_wanandroid.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.bean.Datas

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.adapter
 * @Author: sylvan
 * @Date: 19-7-26
 */
class HomeAdapter(private val context: Context?, data: MutableList<Datas>) :
    BaseQuickAdapter<Datas, BaseViewHolder>(R.layout.home_list_item, data) {
    override fun convert(helper: BaseViewHolder?, item: Datas?) {
        item ?: return
        if (helper == null) return
        if (context == null) return
        helper.setText(R.id.homeItemTitle, item.title)
            .setText(R.id.homeItemAuthor, item.author)
            .setText(R.id.homeItemType, item.chapterName)
            .addOnClickListener(R.id.homeItemType)
            .setTextColor(R.id.homeItemType, context.resources.getColor(R.color.colorPrimary))
            .linkify(R.id.homeItemType)
            .setText(R.id.homeItemDate, item.niceDate)
            .setImageResource(
                R.id.homeItemLike,
                if (item.collect) R.drawable.ic_action_like else R.drawable.ic_action_no_like
            )
            .addOnClickListener(R.id.homeItemLike)
    }
}