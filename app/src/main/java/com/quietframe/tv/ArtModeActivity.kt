package com.quietframe.tv

import android.app.Activity
import android.os.Bundle
import android.widget.FrameLayout

class ArtModeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Simple black screen — no XML, no theme, no crash
        val root = FrameLayout(this)
        root.setBackgroundColor(0xFF000000.toInt())
        setContentView(root)
    }
}
