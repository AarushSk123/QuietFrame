package com.quietframe.tv

import android.content.Context
import fi.iki.elonen.NanoHTTPD

class LocalUploadServer(
    private val context: Context,
    port: Int = 8080
) : NanoHTTPD(port) {

    override fun serve(session: IHTTPSession): Response {
        if (session.method == Method.POST) {
            val files = HashMap<String, String>()
            session.parseBody(files)

            val tmpPath = files["file"] ?: return newFixedLengthResponse("No file")
            val bytes = java.io.File(tmpPath).readBytes()

            val filename = "art_${System.currentTimeMillis()}.jpg"
            ImageStorage.saveImage(context, filename, bytes)

            return newFixedLengthResponse("OK")
        }

        return newFixedLengthResponse("""
            <html>
            <body>
            <h2>Quiet Frame Upload</h2>
            <form method="post" enctype="multipart/form-data">
                <input type="file" name="file"/>
                <input type="submit"/>
            </form>
            </body>
            </html>
        """.trimIndent())
    }
}
