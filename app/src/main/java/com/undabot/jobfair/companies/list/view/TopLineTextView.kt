package com.undabot.jobfair.companies.list.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.annotation.DimenRes
import androidx.appcompat.widget.AppCompatTextView
import com.undabot.jobfair.R

class TopLineTextView : AppCompatTextView {

    private val paint: Paint = Paint()
    private var lineThickness: Float = 0f

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs, defStyle)
    }

    fun init(context: Context, attrs: AttributeSet? = null, defStyle: Int? = null) {
        if (attrs != null) {
            val typeArray = context.theme.obtainStyledAttributes(attrs, R.styleable.TopLineTextView, 0, 0)
            try {
                paint.style = Paint.Style.STROKE
                paint.color = typeArray.getColor(R.styleable.TopLineTextView_lineColor, 0)
                lineThickness = typeArray.getDimension(R.styleable.TopLineTextView_lineThickness, 0f)
            } finally {
                typeArray.recycle()
            }
        }
    }

    fun setLineThickness(@DimenRes lineThickness: Int) {
        this.lineThickness = context.resources.getDimension(lineThickness)
    }

    override fun onDraw(canvas: Canvas) {
        if (lineThickness != 0f) {
            canvas.drawLine(0f, 0f, width.toFloat(), lineThickness, paint)
        }
        super.onDraw(canvas)
    }
} 