package com.quietframe.tv

import android.os.Handler
import android.os.Looper
import android.view.View
import java.util.Calendar
import kotlin.math.abs

class BrightnessController(
    private val view: View
) {

    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval = 5 * 60 * 1000L // 5 min

    fun start() {
        apply()
        handler.postDelayed(updateRunnable, updateInterval)
    }

    fun stop() {
        handler.removeCallbacksAndMessages(null)
    }

    private val updateRunnable = object : Runnable {
        override fun run() {
            apply()
            handler.postDelayed(this, updateInterval)
        }
    }

    private fun apply() {
        val factor = calculateBrightness()
        view.animate()
            .alpha(factor)
            .setDuration(2000)
            .start()
    }

    private fun calculateBrightness(): Float {
        val now = Calendar.getInstance()
        val hour = now.get(Calendar.HOUR_OF_DAY)
        val minute = now.get(Calendar.MINUTE)
        val time = hour + minute / 60f

        return when {
            time < 6f  -> lerp(time, 0f, 6f, 0.45f, 0.85f)
            time < 9f  -> lerp(time, 6f, 9f, 0.85f, 1.0f)
            time < 18f -> lerp(time, 9f, 18f, 1.0f, 0.9f)
            time < 21f -> lerp(time, 18f, 21f, 0.9f, 0.7f)
            time < 24f -> lerp(time, 21f, 24f, 0.7f, 0.5f)
            else       -> 0.5f
        }
    }

    private fun lerp(
        value: Float,
        start: Float,
        end: Float,
        min: Float,
        max: Float
    ): Float {
        val t = (value - start) / (end - start)
        return min + (max - min) * t.coerceIn(0f, 1f)
    }
}
