package com.quietframe.tv

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class LauncherActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Immediately open the art mode screen
        startActivity(Intent(this, ArtModeActivity::class.java))
    }
}