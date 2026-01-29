package com.quietframe.tv

import android.service.dreams.DreamService
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView

class QuietFrameDreamService : DreamService() {

    private lateinit var renderer: ArtRenderer

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        isInteractive = false
        isFullscreen = true

        window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // SAFE fullscreen for AOSP TV
        window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        val imageView = ImageView(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            scaleType = ImageView.ScaleType.CENTER_CROP
        }

        val root = FrameLayout(this).apply {
            setBackgroundColor(0xFF000000.toInt())
            addView(imageView)
        }

        setContentView(root)

        renderer = ArtRenderer(this, imageView)
    }

    override fun onDreamingStarted() {
        super.onDreamingStarted()
        renderer.start()   // 🔥 start art
    }

    override fun onDreamingStopped() {
        renderer.stop()
        super.onDreamingStopped()
    }
}
