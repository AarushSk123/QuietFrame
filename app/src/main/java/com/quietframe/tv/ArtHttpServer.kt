package com.quietframe.tv

import fi.iki.elonen.NanoHTTPD
import java.io.File

class ArtHttpServer(
    private val artDir: File,
    port: Int = 8080
) : NanoHTTPD(port) {

    override fun serve(session: IHTTPSession): Response {

        // Upload image
        if (session.method == Method.POST) {
            val files = HashMap<String, String>()
            session.parseBody(files)

            val tmp = files["file"] ?: return error("No file")

            File(tmp).copyTo(
                File(artDir, "art_${System.currentTimeMillis()}.jpg"),
                overwrite = true
            )

            return redirect("/")
        }

        // Gallery page
        if (session.uri == "/") {
            return gallery()
        }

        // Serve images
        val img = File(artDir, session.uri.removePrefix("/"))
        if (img.exists()) {
            return newChunkedResponse(
                Response.Status.OK,
                "image/*",
                img.inputStream()
            )
        }

        return error("Not found")
    }

    private fun gallery(): Response {
        val html = buildString {
            append("<html><body style='background:#111;color:#fff'>")
            append("<h2>Quiet Frame – Upload Art</h2>")
            append("<form method='post' enctype='multipart/form-data'>")
            append("<input type='file' name='file' accept='image/*'>")
            append("<button>Upload</button></form><hr>")
            artDir.listFiles()?.forEach {
                append("<img src='/${it.name}' style='width:200px;margin:10px'>")
            }
            append("</body></html>")
        }
        return newFixedLengthResponse(html)
    }

    private fun redirect(path: String) =
        newFixedLengthResponse(Response.Status.REDIRECT, "text/plain", "").apply {
            addHeader("Location", path)
        }

    private fun error(msg: String) =
        newFixedLengthResponse(Response.Status.BAD_REQUEST, "text/plain", msg)
}
