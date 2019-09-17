package com.undabot.jobfair.events.list.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.undabot.jobfair.R
import com.undabot.jobfair.utils.dpToPx
import org.joda.time.DateTime

class TimelineDots @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var hasBottomDots: Boolean = true
    var hasTopDots: Boolean = true
    var dotColor: Int = 0
        set(value) {
            field = value
            dotPaint.color = value
        }
    var pastDotColor: Int = 0
        set(value) {
            field = value
            pastDotPaint.color = value
        }
    var presentInsideDotColor: Int = 0
        set(value) {
            field = value
            presentInsideDotPaint.color = value
        }
    var presentOutsideDotColor: Int = 0
        set(value) {
            field = value
            presentOutsideDotPaint.color = value
        }
    var futureDotColor: Int = 0
        set(value) {
            field = value
            futureDotPaint.color = value
        }
    var timeIndicator: TimeIndicator = TimeIndicator.PAST
    var dotSize: Float
    var pastDotSize: Float
    var presentInsideDotSize: Float
    var presentOutsideDotSize: Float
    var futureDotSize: Float
    var numberOfDots: Int

    private var dotPaint: Paint = Paint()
    private var pastDotPaint: Paint = Paint()
    private var presentInsideDotPaint: Paint = Paint()
    private var presentOutsideDotPaint: Paint = Paint()
    private var futureDotPaint: Paint = Paint()

    private var rect: RectF = RectF()

    private val paddingBetweenDots = DEFAULT_PADDING_DP.dpToPx(context)

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.TimelineDots, defStyleAttr, 0)
        hasBottomDots = a.getBoolean(R.styleable.TimelineDots_bottom, true)
        hasTopDots = a.getBoolean(R.styleable.TimelineDots_top, true)

        dotColor = a.getColor(R.styleable.TimelineDots_dotColor, 0)
        pastDotColor = a.getColor(R.styleable.TimelineDots_pastDotColor, 0)
        presentInsideDotColor = a.getColor(R.styleable.TimelineDots_presentInsideDotColor, 0)
        presentOutsideDotColor = a.getColor(R.styleable.TimelineDots_presentOutsideDotColor, 0)
        futureDotColor = a.getColor(R.styleable.TimelineDots_futureDotColor, 0)

        dotSize = a.getDimension(R.styleable.TimelineDots_dotSize, 0f)
        pastDotSize = a.getDimension(R.styleable.TimelineDots_pastDotSize, 0f)
        presentInsideDotSize = a.getDimension(R.styleable.TimelineDots_presentInsideDotSize, 0f)
        presentOutsideDotSize = a.getDimension(R.styleable.TimelineDots_presentOutsideDotSize, 0f)
        futureDotSize = a.getDimension(R.styleable.TimelineDots_futureDotSize, 0f)

        numberOfDots = a.getInt(R.styleable.TimelineDots_numberOfDots, DEFAULT_NUMBER_OF_DOTS)
        a.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        if (hasTopDots) {
            drawTopDots(canvas)
        }

        when (timeIndicator) {
            TimeIndicator.PAST -> drawPastDot(canvas)
            TimeIndicator.PRESENT -> drawPresentDot(canvas)
            TimeIndicator.FUTURE -> drawFutureDot(canvas)
        }

        if (hasBottomDots) {
            drawBottomDots(canvas)
        }
    }

    private fun drawTopDots(canvas: Canvas) {
        val from = 0f
        val to = (height / 2) - (presentOutsideDotSize / 2) - paddingBetweenDots
        drawDots(canvas, width / 2f, from, to)
    }

    private fun drawBottomDots(canvas: Canvas) {
        val from = height / 2 + (presentOutsideDotSize / 2) + paddingBetweenDots
        val to = height.toFloat()
        drawDots(canvas, width / 2f, from, to)
    }

    private fun drawDots(canvas: Canvas, x: Float, from: Float, to: Float) {
        val totalHeightOfOneDot = (to - from) / numberOfDots
        val paddingBetweenDots = totalHeightOfOneDot - dotSize

        for (i in 0 until numberOfDots) {
            val y = from + (totalHeightOfOneDot * i) + paddingBetweenDots / 2 + dotSize / 2
            calculateRect(x, y, dotSize)
            canvas.drawOval(rect, dotPaint)
        }
    }

    private fun drawPastDot(canvas: Canvas) {
        calculateRect(width / 2f, height / 2f, pastDotSize)
        canvas.drawOval(rect, pastDotPaint)
    }

    private fun drawPresentDot(canvas: Canvas) {
        calculateRect(width / 2f, height / 2f, presentOutsideDotSize)
        canvas.drawOval(rect, presentOutsideDotPaint)

        calculateRect(width / 2f, height / 2f, presentInsideDotSize)
        canvas.drawOval(rect, presentInsideDotPaint)
    }

    private fun drawFutureDot(canvas: Canvas) {
        calculateRect(width / 2f, height / 2f, futureDotSize)
        canvas.drawOval(rect, futureDotPaint)
    }

    private fun calculateRect(x: Float, y: Float, size: Float) {
        rect.left = x - size / 2f
        rect.right = x + size / 2f
        rect.top = y - size / 2f
        rect.bottom = y + size / 2f
    }

    companion object {
        private const val DEFAULT_PADDING_DP = 4
        private const val DEFAULT_NUMBER_OF_DOTS = 10
    }

    enum class TimeIndicator {
        PAST,
        PRESENT,
        FUTURE;

        companion object {
            fun from(startTime: DateTime?, endTime: DateTime?): TimeIndicator {
                return if (startTime != null && endTime != null) {
                    val now = DateTime.now()
                    when {
                        startTime > now -> FUTURE
                        startTime <= now && now <= endTime -> PRESENT
                        now > endTime -> PAST
                        else -> PAST
                    }
                } else {
                    PAST
                }
            }
        }
    }
}