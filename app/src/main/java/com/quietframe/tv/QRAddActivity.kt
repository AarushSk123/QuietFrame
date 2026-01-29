package com.quietframe.tv

import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

class QRAddActivity : Activity() {

    private lateinit var server: LocalUploadServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        server = LocalUploadServer(this)
        server.start()

        val url = "http://192.168.1.100:8080" // change if needed

        val qrBitmap = generateQR(url)

        val image = ImageView(this).apply {
            setImageBitmap(qrBitmap)
            setBackgroundColor(0xFF202020.toInt())
        }

        setContentView(image)
    }

    override fun onDestroy() {
        super.onDestroy()
        server.stop()
    }

    private fun generateQR(text: String): Bitmap {
        val writer = QRCodeWriter()
        val matrix = writer.encode(text, BarcodeFormat.QR_CODE, 512, 512)

        val bmp = Bitmap.createBitmap(512, 512, Bitmap.Config.RGB_565)
        for (x in 0 until 512) {
            for (y in 0 until 512) {
                bmp.setPixel(x, y, if (matrix[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt())
            }
        }
        return bmp
    }
}
