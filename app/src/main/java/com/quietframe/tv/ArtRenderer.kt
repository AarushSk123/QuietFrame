package com.quietframe.tv

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.core.content.ContextCompat

class ArtRenderer(
    private val context: Context,
    private val imageView: ImageView
) {

    private val handler = Handler(Looper.getMainLooper())
    private var index = 0

    // SAFE hardcoded images (replace later)
    private val artImages = listOf(
        R.drawable.art1,
        R.drawable.art2,
        R.drawable.art3
    )

    private val switchRunnable = object : Runnable {
        override fun run() {
            index = (index + 1) % artImages.size
            imageView.setImageDrawable(
                ContextCompat.getDrawable(context, artImages[index])
            )
            handler.postDelayed(this, ArtConfig.getSwitchInterval(context))
        }
    }

    fun start() {
        imageView.setImageDrawable(
            ContextCompat.getDrawable(context, artImages[index])
        )
        handler.postDelayed(switchRunnable, ArtConfig.getSwitchInterval(context))
    }

    fun stop() {
        handler.removeCallbacks(switchRunnable)
    }
}
