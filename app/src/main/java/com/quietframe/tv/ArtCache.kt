package com.quietframe.tv

import android.content.Context
import java.io.File

class ArtCache(private val context: Context) {

    fun next(): File {
        val dir = File(context.filesDir, "art")
        dir.mkdirs()

        val files = dir.listFiles()
            ?: throw IllegalStateException("No art files")

        return files.random()
    }
}
