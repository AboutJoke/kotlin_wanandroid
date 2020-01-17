package com.sylvan.kotlin_wanandroid.adapter

import android.content.Context
import android.text.Html
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.bean.Article

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.adapter
 * @Author: sylvan
 * @Date: 20-1-16
 */
class SquareAdapter(private val context: Context?, data: MutableList<Article>) :
    BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_square_layout, data) {
    override fun convert(helper: BaseViewHolder?, item: Article?) {
        item ?: return
        context ?: return
        helper ?: return
        val authorStr = if (item.author.isNotEmpty()) item.author else item.shareUser
        helper.setText(R.id.tv_article_title, Html.fromHtml(item.title))
            .setText(R.id.tv_article_author, authorStr)
            .setText(R.id.tv_article_date, item.niceDate)
            .setImageResource(
                R.id.iv_like,
                if (item.collect) R.drawable.ic_action_like else R.drawable.ic_action_no_like
            )
            .addOnClickListener(R.id.iv_like)
        val chapterName = when {
            item.superChapterName.isNotEmpty() and item.chapterName.isNotEmpty() ->
                "${item.superChapterName} / ${item.chapterName}"
            item.superChapterName.isNotEmpty() -> item.superChapterName
            item.chapterName.isNotEmpty() -> item.chapterName
            else -> ""
        }
        helper.setText(R.id.tv_article_chapterName, chapterName)
//        if (item.envelopePic.isNotEmpty()) {
//            helper.getView<ImageView>(R.id.iv_article_thumbnail)
//                .visibility = View.VISIBLE
//            context?.let {
//                Glide.with(it).load(item.envelopePic).placeholder(R.drawable.ic_launcher_foreground)
//                    .into(helper.getView(R.id.iv_article_thumbnail))
//            }
//        } else {
//            helper.getView<ImageView>(R.id.iv_article_thumbnail)
//                .visibility = View.GONE
//        }
        val tv_fresh = helper.getView<TextView>(R.id.tv_article_fresh)
        if (item.fresh) {
            tv_fresh.visibility = View.VISIBLE
        } else {
            tv_fresh.visibility = View.GONE
        }
        val tv_top = helper.getView<TextView>(R.id.tv_article_top)
        if (item.top == "1") {
            tv_top.visibility = View.VISIBLE
        } else {
            tv_top.visibility = View.GONE
        }
        val tv_article_tag = helper.getView<TextView>(R.id.tv_article_tag)
        if (item.tags.size > 0) {
            tv_article_tag.visibility = View.VISIBLE
            tv_article_tag.text = item.tags[0].name
        } else {
            tv_article_tag.visibility = View.GONE
        }
    }
}