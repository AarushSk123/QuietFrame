package com.quietframe.tv.upload

import android.content.Context
import fi.iki.elonen.NanoHTTPD
import java.io.File

class LocalUploadServer(private val context: Context) : NanoHTTPD(8080) {

    override fun serve(session: IHTTPSession): Response {
        if (session.method == Method.POST) {
            val files = HashMap<String, String>()
            session.parseBody(files)

            val tmp = File(files["file"] ?: return bad())
            val dir = File(context.filesDir, "art")
            dir.mkdirs()

            tmp.copyTo(File(dir, "upload_${System.currentTimeMillis()}.jpg"))

            return newFixedLengthResponse("OK")
        }
        return bad()
    }

    private fun bad() =
        newFixedLengthResponse(Response.Status.BAD_REQUEST, "text/plain", "Bad request")
}
