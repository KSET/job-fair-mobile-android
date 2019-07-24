package com.undabot.jobfair.scanqr.mapper

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.undabot.jobfair.login.models.User
import com.undabot.jobfair.scanqr.entity.QrCodeData
import javax.inject.Inject

class UserQrCodeMapper @Inject constructor(
    private val jsonMapper: QrCodeDataJsonMapper
) {
    fun map(user: User?, size: Int = DEFAULT_QR_BITMAP_SIZE): Bitmap? {
        return if (user != null) {
            val qrCodeData = QrCodeData(
                resumeId = user.resume?.uid.orEmpty(),
                firstName = user.firstName,
                lastName = user.lastName
            )
            val json = jsonMapper.toJson(qrCodeData)

            encodeAsBitmap(json, BarcodeFormat.QR_CODE, size, size)
        } else {
            null
        }
    }

    @Throws(WriterException::class)
    fun encodeAsBitmap(contents: String, format: BarcodeFormat, desiredWidth: Int, desiredHeight: Int): Bitmap {
        val result = MultiFormatWriter().encode(
            contents,
            format,
            desiredWidth,
            desiredHeight,
            mapOf(EncodeHintType.CHARACTER_SET to "UTF-8")
        )
        val pixels = result.getPixels()

        return Bitmap.createBitmap(result.width, result.height, Bitmap.Config.ARGB_8888).apply {
            setPixels(pixels, 0, width, 0, 0, width, height)
        }
    }

    private fun BitMatrix.getPixels(): IntArray {
        val pixels = IntArray(width * height)

        for (y in 0 until height) {
            val offset = y * width
            for (x in 0 until width) {
                pixels[offset + x] = if (get(x, y)) {
                    Color.BLACK
                } else {
                    Color.WHITE
                }
            }
        }

        return pixels
    }

    companion object {
        private const val DEFAULT_QR_BITMAP_SIZE = 512
    }
}