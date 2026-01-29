package com.quietframe.tv.upload

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

object QRCodeUtil {

    fun generate(url: String, size: Int = 300): Bitmap {
        val matrix = QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, size, size)
        val bmp = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)

        for (x in 0 until size)
            for (y in 0 until size)
                bmp.setPixel(
                    x, y,
                    if (matrix[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt()
                )

        return bmp
    }
}
