package com.quietframe.tv

import android.app.Activity
import android.os.Bundle
import android.text.InputType
import android.widget.*
import android.view.Gravity

class SettingsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(60, 60, 60, 60)
        }

        val title = TextView(this).apply {
            text = "Art Switch Interval (seconds)"
            textSize = 18f
        }

        val input = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_NUMBER
            setText((ArtConfig.getSwitchInterval(this@SettingsActivity) / 1000).toString())
        }

        val saveBtn = Button(this).apply {
            text = "Save"
            setOnClickListener {
                val seconds = input.text.toString().toLongOrNull() ?: 15
                ArtConfig.setSwitchInterval(
                    this@SettingsActivity,
                    seconds * 1000
                )
                Toast.makeText(
                    this@SettingsActivity,
                    "Saved: $seconds seconds",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val testBtn = Button(this).apply {
            text = "Test Art Mode"
            setOnClickListener {
                startActivity(
                    android.content.Intent(
                        this@SettingsActivity,
                        TestArtActivity::class.java
                    )
                )
            }
        }

        layout.addView(title)
        layout.addView(input)
        layout.addView(saveBtn)
        layout.addView(testBtn)

        setContentView(layout)
    }
}
