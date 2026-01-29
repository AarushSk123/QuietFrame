package com.quietframe.tv

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class SensorController(private val activity: Activity) {

    private val manager =
        activity.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    fun start() {
        val sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT) ?: return
        manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    private val listener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val lux = event.values[0]
            val params = activity.window.attributes
            params.screenBrightness = (lux / 1200f).coerceIn(0.05f, 0.3f)
            activity.window.attributes = params
        }
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
    }
}
