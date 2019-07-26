package com.sylvan.kotlin_wanandroid.widget


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.TextView
import com.sylvan.kotlin_wanandroid.R
import java.util.*
import kotlin.math.min

/**
 * @ClassName: com.sylvan.kotlin_wanandroid.widget
 * @Author: sylvan
 * @Date: 19-7-3
 *
 * 解决中英文标点符号混合下造成的排版问题
 */

class AlignTextView : TextView {
    private var textHeight: Float = 0.toFloat() // 单行文字高度
    private var textLineSpaceExtra = 0f // 额外的行间距
    private var viewWidth: Int = 0 // textView宽度
    private val lines = ArrayList<String>() // 分割后的行
    private val tailLines = ArrayList<Int>() // 尾行
    private var align = Align.ALIGN_LEFT // 默认最后一行左对齐
    private var firstCalc = true  // 初始化计算

    private var mLineSpacingMultiplier = 1.0f
    private var lineSpacingAdd = 0.0f

    private var originalHeight = 0 //原始高度
    private var originalLineCount = 0 //原始行数
    private var originalPaddingBottom = 0 //原始paddingBottom
    private var setPaddingFromMe = false

    // 尾行对齐方式
    enum class Align {
        ALIGN_LEFT, ALIGN_CENTER, ALIGN_RIGHT  // 居中，居左，居右,针对段落最后一行
    }

    constructor(context: Context) : super(context) {
        setTextIsSelectable(false)
    }

    @SuppressLint("ResourceType")
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setTextIsSelectable(false)

        val attributes = intArrayOf(android.R.attr.lineSpacingExtra, android.R.attr.lineSpacingMultiplier)
        val arr = context.obtainStyledAttributes(attrs, attributes)
        lineSpacingAdd = arr.getDimensionPixelSize(0, 0).toFloat()
        mLineSpacingMultiplier = arr.getFloat(1, 1.0f)
        originalPaddingBottom = paddingBottom
        arr.recycle()

        val ta = context.obtainStyledAttributes(attrs, R.styleable.AlignTextView)

        align = when (ta.getInt(R.styleable.AlignTextView_AlignTextView_align, 0)) {
            1 -> Align.ALIGN_CENTER
            2 -> Align.ALIGN_RIGHT
            else -> Align.ALIGN_LEFT
        }

        ta.recycle()
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        //首先进行高度调整
        if (firstCalc) {
            viewWidth = measuredWidth
            val text = text.toString()
            val paint = paint
            lines.clear()
            tailLines.clear()

            // 文本含有换行符时，分割单独处理
            val items = text.split("\\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (item in items) {
                calc(paint, item)
            }

            //使用替代textview计算原始高度与行数
            measureTextViewHeight(
                text, paint.textSize, measuredWidth -
                        paddingLeft - paddingRight
            )

            //获取行高
            textHeight = 1.0f * originalHeight / originalLineCount

            textLineSpaceExtra = textHeight * (mLineSpacingMultiplier - 1) + lineSpacingAdd

            //计算实际高度,加上多出的行的高度(一般是减少)
            val heightGap = ((textLineSpaceExtra + textHeight) * (lines.size - originalLineCount)).toInt()

            setPaddingFromMe = true
            //调整textview的paddingBottom来缩小底部空白
            setPadding(
                paddingLeft, paddingTop, paddingRight,
                originalPaddingBottom + heightGap
            )

            firstCalc = false
        }
    }

    override fun onDraw(canvas: Canvas) {
        val paint = paint
        paint.color = currentTextColor
        paint.drawableState = drawableState

        viewWidth = measuredWidth

        val fm = paint.fontMetrics
        var firstHeight = textSize - (fm.bottom - fm.descent + fm.ascent - fm.top)

        val gravity = gravity
        if (gravity and 0x1000 == 0) { // 是否垂直居中
            firstHeight += (textHeight - firstHeight) / 2
        }

        val paddingTop = paddingTop
        val paddingLeft = paddingLeft
        val paddingRight = paddingRight
        viewWidth = viewWidth - paddingLeft - paddingRight

        for (i in lines.indices) {
            val drawY = i * textHeight + firstHeight
            val line = lines[i]
            // 绘画起始x坐标
            var drawSpacingX = paddingLeft.toFloat()
            val gap = viewWidth - paint.measureText(line)
            var interval = gap / (line.length - 1)

            // 绘制最后一行
            if (tailLines.contains(i)) {
                interval = 0f
                if (align == Align.ALIGN_CENTER) {
                    drawSpacingX += gap / 2
                } else if (align == Align.ALIGN_RIGHT) {
                    drawSpacingX += gap
                }
            }

            for (j in 0 until line.length) {
                val drawX = paint.measureText(line.substring(0, j)) + interval * j
                canvas.drawText(
                    line.substring(j, j + 1), drawX + drawSpacingX, drawY +
                            paddingTop.toFloat() + textLineSpaceExtra * i, paint
                )
            }
        }
    }

    /**
     * 设置尾行对齐方式
     *
     * @param align 对齐方式
     */
    fun setAlign(align: Align) {
        this.align = align
        invalidate()
    }

    /**
     * 计算每行应显示的文本数
     *
     * @param text 要计算的文本
     */
    private fun calc(paint: Paint, text: String) {
        if (text.isEmpty()) {
            lines.add("\n")
            return
        }
        var startPosition = 0 // 起始位置
        val oneChineseWidth = paint.measureText("中")
        val ignoreCalcLength = (viewWidth / oneChineseWidth).toInt() // 忽略计算的长度
        var sb = StringBuilder(
            text.substring(
                0, min(ignoreCalcLength + 1, text.length)
            )
        )

        var i = ignoreCalcLength + 1
        while (i < text.length) {
            if (paint.measureText(text.substring(startPosition, i + 1)) > viewWidth) {
                startPosition = i
                //将之前的字符串加入列表中
                lines.add(sb.toString())

                sb = StringBuilder()

                //添加开始忽略的字符串，长度不足的话直接结束,否则继续
                if (text.length - startPosition > ignoreCalcLength) {
                    sb.append(text.substring(startPosition, startPosition + ignoreCalcLength))
                } else {
                    lines.add(text.substring(startPosition))
                    break
                }

                i = i + ignoreCalcLength - 1
            } else {
                sb.append(text[i])
            }
            i++
        }
        if (sb.isNotEmpty()) {
            lines.add(sb.toString())
        }

        tailLines.add(lines.size - 1)
    }


    override fun setText(text: CharSequence, type: BufferType) {
        firstCalc = true
        super.setText(text, type)
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        if (!setPaddingFromMe) {
            originalPaddingBottom = bottom
        }
        setPaddingFromMe = false
        super.setPadding(left, top, right, bottom)
    }


    /**
     * 获取文本实际所占高度，辅助用于计算行高,行数
     *
     * @param text        文本
     * @param textSize    字体大小
     * @param deviceWidth 屏幕宽度
     */
    private fun measureTextViewHeight(text: String, textSize: Float, deviceWidth: Int) {
        val textView = TextView(context)
        textView.text = text
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        val widthMeasureSpec = MeasureSpec.makeMeasureSpec(deviceWidth, MeasureSpec.EXACTLY)
        val heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
        textView.measure(widthMeasureSpec, heightMeasureSpec)
        originalLineCount = textView.lineCount
        originalHeight = textView.measuredHeight
    }
}