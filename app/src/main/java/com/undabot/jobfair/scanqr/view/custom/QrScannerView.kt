package com.undabot.jobfair.scanqr.view.custom

import android.content.Context
import android.util.AttributeSet
import com.google.zxing.BarcodeFormat
import me.dm7.barcodescanner.core.IViewFinder
import me.dm7.barcodescanner.zxing.ZXingScannerView

class QrScannerView : ZXingScannerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        setFormats(FORMATS)
    }

    override fun createViewFinderView(context: Context): IViewFinder = ViewFinder(context)

    companion object {
        private val FORMATS = listOf(BarcodeFormat.QR_CODE)
    }
}