package com.undabot.jobfair.scanqr.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.undabot.jobfair.R
import me.dm7.barcodescanner.core.ViewFinderView

class ViewFinder : ViewFinderView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        setBorderColor(ContextCompat.getColor(context, R.color.white))
        setBorderCornerRadius(resources.getDimension(R.dimen.card_radius).toInt())
        setLaserEnabled(false)
    }

    override fun drawViewFinderBorder(canvas: Canvas) {
        val framingRect = framingRect

        // Left border
        val path = Path()
        path.moveTo((framingRect.left + mBorderLineLength).toFloat(), framingRect.bottom.toFloat())
        path.lineTo(framingRect.left.toFloat(), framingRect.bottom.toFloat())
        path.lineTo(framingRect.left.toFloat(), framingRect.top.toFloat())
        path.lineTo((framingRect.left + mBorderLineLength).toFloat(), framingRect.top.toFloat())
        canvas.drawPath(path, mBorderPaint)

        // Right border
        path.moveTo((framingRect.right - mBorderLineLength).toFloat(), framingRect.bottom.toFloat())
        path.lineTo(framingRect.right.toFloat(), framingRect.bottom.toFloat())
        path.lineTo(framingRect.right.toFloat(), framingRect.top.toFloat())
        path.lineTo((framingRect.right - mBorderLineLength).toFloat(), framingRect.top.toFloat())
        canvas.drawPath(path, mBorderPaint)
    }
}