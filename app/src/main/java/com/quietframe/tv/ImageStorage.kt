package com.quietframe.tv

import android.content.Context
import java.io.File
import java.io.FileOutputStream

object ImageStorage {

    fun artDir(context: Context): File {
        val dir = File(context.filesDir, "art")
        if (!dir.exists()) dir.mkdirs()
        return dir
    }

    fun saveImage(context: Context, name: String, data: ByteArray) {
        val file = File(artDir(context), name)
        FileOutputStream(file).use { it.write(data) }
    }

    fun listImages(context: Context): List<File> {
        return artDir(context).listFiles()?.toList() ?: emptyList()
    }
}
