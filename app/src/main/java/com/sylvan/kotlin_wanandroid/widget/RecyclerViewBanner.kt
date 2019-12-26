package com.sylvan.kotlin_wanandroid.widget

import Constant
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sylvan.kotlin_wanandroid.R
import com.sylvan.kotlin_wanandroid.bean.BannerResponse
import com.sylvan.kotlin_wanandroid.ui.WebViewAct
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.widget
 * @Author: sylvan
 * @Date: 19-7-29
 */
class RecyclerViewBanner : FrameLayout {

    companion object {
        private const val DEFAULT_INTERVAL = 3000L
    }

    private val datas = mutableListOf<BannerResponse.Data>()
    private val bannerAdapter: BannerAdapter by lazy {
        BannerAdapter(context, datas)
    }
    private var banner: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var currentIndex = 0
    private var bannerSwitchJob: Job? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        banner = RecyclerView(context)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(banner)

        banner?.run {
            linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager?.orientation = LinearLayoutManager.HORIZONTAL
            linearLayoutManager?.initialPrefetchItemCount = 1
            layoutManager = linearLayoutManager
            adapter = bannerAdapter
            addOnScrollListener(onScrollChangeListener)
        }

        bannerAdapter.run {
            bindToRecyclerView(banner)
            setOnItemClickListener { _, _, position ->
                if (datas.size != 0) {
                    Intent(context, WebViewAct::class.java).run {
                        putExtra(Constant.CONTENT_URL_KEY, datas[position].url)
                        putExtra(Constant.CONTENT_ID_KEY, datas[position].id)
                        putExtra(Constant.CONTENT_TITLE_KEY, datas[position].title)
                        context.startActivity(this)
                    }
                }
            }
        }

        val layoutParams =
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addView(banner, layoutParams)
    }

    private val onScrollChangeListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
        }
    }

    private fun getBannerSwitchJob() = GlobalScope.launch {
        repeat(Int.MAX_VALUE) {
            if (datas.size == 0) {
                return@launch
            }
            delay(DEFAULT_INTERVAL)
            currentIndex++
            val index = currentIndex % datas.size
            banner?.smoothScrollToPosition(index)
            currentIndex = index
        }
    }

    fun setBannerData(datas: List<BannerResponse.Data>) {
        this.datas.clear()
        this.datas.addAll(datas)
        bannerAdapter.replaceData(datas)
        startSwitchJob()
    }

    private fun startSwitchJob() = bannerSwitchJob?.run {
        if (!isActive) {
            bannerSwitchJob = getBannerSwitchJob().apply { start() }
        }
    } ?: let {
        bannerSwitchJob = getBannerSwitchJob().apply { start() }
    }

    fun cancelSwitchJob() = bannerSwitchJob?.run {
        if (isActive) {
            cancel()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        cancelSwitchJob()
    }

    override fun onVisibilityChanged(@NonNull changedView: View, visibility: Int) {
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            // 停止轮播
            cancelSwitchJob()
        } else if (visibility == View.VISIBLE) {
            // 开始轮播
            startSwitchJob()
        }
        super.onVisibilityChanged(changedView, visibility)
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            // 停止轮播
            cancelSwitchJob()
        } else if (visibility == View.VISIBLE) {
            // 开始轮播
            startSwitchJob()
        }
        super.onWindowVisibilityChanged(visibility)
    }

    inner class BannerAdapter(private val context: Context, datas: MutableList<BannerResponse.Data>) :
        BaseQuickAdapter<BannerResponse.Data, BaseViewHolder>(R.layout.item_banner, datas) {
        override fun convert(helper: BaseViewHolder, item: BannerResponse.Data?) {
//            val i = helper.adapterPosition % datas.size
//            val data = datas[i]
//            data?.run {
//                helper.setText(R.id.banner_desc, data.title)
//                val imageView = helper.getView<ImageView>(R.id.banner_img)
//                Glide.with(context).load(data.imagePath).placeholder(R.drawable.ic_launcher_foreground).into(imageView)
//            }
            item?.run {
                helper.setText(R.id.banner_desc, item.title)
                val imageView = helper.getView<ImageView>(R.id.banner_img)
                Glide.with(context).load(item.imagePath).placeholder(R.drawable.ic_launcher_foreground).into(imageView)
            }
        }

        override fun getItemCount(): Int {
//            return if (mData == null) 0 else if (mData.size < 2) mData.size else Integer.MAX_VALUE
            return if (mData == null) 0 else mData.size
        }

    }
}