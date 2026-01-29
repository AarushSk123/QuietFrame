package com.quietframe.tv.sources

import android.content.Context
import java.io.File
import java.net.URL

class WindowsSightSource(private val context: Context) {

    fun ingest(urls: List<String>) {
        val dir = File(context.filesDir, "art")
        dir.mkdirs()

        urls.forEachIndexed { i, url ->
            val file = File(dir, "windowsight_$i.jpg")
            if (!file.exists()) {
                URL(url).openStream().use { input ->
                    file.outputStream().use { input.copyTo(it) }
                }
            }
        }
    }
}
