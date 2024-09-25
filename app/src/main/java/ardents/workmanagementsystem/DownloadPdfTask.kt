package ardents.workmanagementsystem

import android.content.Context
import android.os.AsyncTask
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadPdfTask(
    private val context: Context,
    private val callback: (File?) -> Unit
) : AsyncTask<String, Void, File?>() {

    override fun doInBackground(vararg urls: String?): File? {
        val url = urls[0] ?: return null
        return try {
            val urlConnection = URL(url).openConnection() as HttpURLConnection
            urlConnection.connect()
            val inputStream: InputStream = urlConnection.inputStream

            val file =
                File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "temp.pdf")
            val outputStream = FileOutputStream(file)

            inputStream.copyTo(outputStream)
            outputStream.close()
            inputStream.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun onPostExecute(result: File?) {
        callback(result)
    }
}