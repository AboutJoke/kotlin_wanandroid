package com.sylvan.kotlin_wanandroid.adapter

import android.content.Context
import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.bean.SystemResponse

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.adapter
 * @Author: sylvan
 * @Date: 20-1-17
 */
class SystemAdapter(private val context: Context?, datas: MutableList<SystemResponse.Data>) :
    BaseQuickAdapter<SystemResponse.Data, BaseViewHolder>(
        R.layout.item_system_layout, datas
    ) {

    override fun convert(helper: BaseViewHolder?, item: SystemResponse.Data?) {
        helper?.setText(R.id.title_first, item?.name)

        item?.children.let {
            helper?.setText(
                R.id.title_second,
                it?.joinToString("    ", transform = { child ->
                    Html.fromHtml(child.name)
                })
            )

        }
    }

}