package com.quietframe.tv

import android.graphics.Bitmap
import androidx.collection.LruCache

class ImageCache {

    private val cache = object : LruCache<Int, Bitmap>(8 * 1024 * 1024) {}

    fun get(key: Int, loader: () -> Bitmap): Bitmap {
        return cache[key] ?: loader().also {
            cache.put(key, it)
        }
    }
}
