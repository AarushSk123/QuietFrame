package com.quietframe.tv

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class ImageRepository(private val context: Context) {

    private var index = 0

    private val images = listOf(
        R.drawable.art1,
        R.drawable.art2,
        R.drawable.art3
    )

    fun getNextImage(): Bitmap {
        val resId = images[index]
        index = (index + 1) % images.size

        return BitmapFactory.decodeResource(context.resources, resId)
    }
}
