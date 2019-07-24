package com.undabot.jobfair.core.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.undabot.jobfair.R

class RoundedImageView : AppCompatImageView {

    private var cornerRadiusInPx = 0f

    private val rectangle = RectF(0f, 0f, 0f, 0f)
    private val clipPath = Path()

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
            val typeArray = context.theme.obtainStyledAttributes(attrs, R.styleable.RoundedImageView, 0, 0)
            try {
                cornerRadiusInPx = typeArray.getDimension(R.styleable.RoundedImageView_cornerRadius, 0f)
            } finally {
                typeArray.recycle()
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(heightMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        rectangle.right = width.toFloat()
        rectangle.bottom = height.toFloat()
        clipPath.addRoundRect(rectangle, cornerRadiusInPx, cornerRadiusInPx, Path.Direction.CW)

        canvas.clipPath(clipPath)

        super.onDraw(canvas)
    }
}