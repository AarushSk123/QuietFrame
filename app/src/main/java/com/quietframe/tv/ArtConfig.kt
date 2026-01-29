package com.quietframe.tv

import android.content.Context

object ArtConfig {

    private const val PREFS = "quiet_frame_prefs"
    private const val KEY_INTERVAL = "switch_interval_ms"

    private const val DEFAULT_INTERVAL = 15_000L // 15 seconds

    fun getSwitchInterval(context: Context): Long {
        return context
            .getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getLong(KEY_INTERVAL, DEFAULT_INTERVAL)
    }

    fun setSwitchInterval(context: Context, valueMs: Long) {
        context
            .getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putLong(KEY_INTERVAL, valueMs)
            .apply()
    }
}
