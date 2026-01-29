package com.quietframe.tv

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView

class TestArtActivity : Activity() {

    private lateinit var renderer: ArtRenderer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val imageView = ImageView(this).apply {
            scaleType = ImageView.ScaleType.CENTER_CROP
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        }

        setContentView(imageView)

        renderer = ArtRenderer(this, imageView)
    }

    override fun onResume() {
        super.onResume()
        renderer.start()
    }

    override fun onPause() {
        super.onPause()
        renderer.stop()
    }
}
