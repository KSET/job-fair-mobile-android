package com.undabot.jobfair.home.view.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.undabot.jobfair.R
import kotlin.math.roundToInt

class HalfCircleLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    private var deviceWidth: Int = 0
    private var outterRadius: Int = 0
    private var innerRadius: Int = 0

    private var backgroundPaint: Paint = Paint()
    private var circlePaint: Paint = Paint()

    init {
        init(context, attrs, defStyleAttr)
        clipChildren = false
    }

    private fun init(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) {
        val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val deviceDisplay = Point()
        display.getSize(deviceDisplay)
        deviceWidth = deviceDisplay.x

        val a = context.obtainStyledAttributes(attrs, R.styleable.HalfCircleLayout, defStyleAttr, 0)
        innerRadius = a.getDimensionPixelSize(R.styleable.HalfCircleLayout_innerRadius, 0)
        outterRadius = a.getDimensionPixelSize(R.styleable.HalfCircleLayout_outterRadius, 0)
        val backgroundColor = a.getColor(R.styleable.HalfCircleLayout_backgroundColor, 0)
        val circleColor = a.getColor(R.styleable.HalfCircleLayout_circleColor, 0)
        a.recycle()

        backgroundPaint.color = backgroundColor
        circlePaint.color = circleColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var maxChildHeight = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility != View.GONE) {
                measureChild(child, widthMeasureSpec, heightMeasureSpec)
                if (child.measuredHeight > maxChildHeight) {
                    maxChildHeight = child.measuredHeight
                }
            }
        }

        val desiredHeight = innerRadius + maxChildHeight / 2
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(heightMeasureSpec)
            MeasureSpec.AT_MOST -> Math.min(desiredHeight, MeasureSpec.getSize(heightMeasureSpec))
            MeasureSpec.UNSPECIFIED -> desiredHeight
            else -> desiredHeight
        }

        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, heightMode))
    }

    override fun dispatchDraw(canvas: Canvas?) {
        canvas?.apply {
            val width = measuredWidth.toFloat()
            val height = measuredHeight.toFloat()
            val radius = outterRadius.toFloat()

            val centerX = width / 2

            drawRect(0f, 0f, width, height, backgroundPaint)
            drawOval(
                -centerX * 0.1f,
                height - radius,
                centerX * 2.1f,
                height + radius,
                circlePaint
            )
        }
        super.dispatchDraw(canvas)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val parentWidth = measuredWidth - paddingLeft - paddingRight
        val parentHeight = measuredHeight - paddingTop - paddingBottom

        val centerX = parentWidth / 2
        val centerY = parentHeight

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val currentAngle = (childCount - i) * Math.PI / (childCount + 1)

            if (child.visibility != View.GONE) {
                val childMeasuredWidth = child.measuredWidth
                val childMeasuredHeight = child.measuredHeight

                val childCenterX = centerX + innerRadius * Math.cos(currentAngle)
                val childCenterY = centerY - innerRadius * Math.sin(currentAngle)

                val top = (childCenterY - childMeasuredHeight / 2).roundToInt()
                val bottom = top + childMeasuredHeight
                val left = (childCenterX - childMeasuredWidth / 2).roundToInt()
                val right = left + childMeasuredWidth

                child.layout(left, top, right, bottom)
            }
        }
    }
}