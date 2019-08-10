package com.mredrock.cyxbs.freshman.presenter

import android.os.Environment
import android.util.Log
import com.mredrock.cyxbs.common.network.ApiGenerator
import com.mredrock.cyxbs.freshman.service.DownloadService
import com.mredrock.cyxbs.freshman.view.iview.IDownloadView
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

/**
 * Created by tk on 2019/8/9
 */
class DownloadPresenter(var iDownloadView: IDownloadView?): BasePresenter {
    fun download(url: String) {
        Log.d("downurl",url)
        val downloadService = ApiGenerator.getApiService(DownloadService::class.java)
        val call = downloadService.getReponse(url)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Thread(Runnable {
                    if (response.body()?.let { saveToCard(url, it) }!!) {
                        iDownloadView?.toastSuccess()
                    } else {
                        iDownloadView?.toastFailed()
                    }
                }).start()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                iDownloadView?.toastFailed()
            }
        })
    }

    fun saveToCard(url: String, responseBody: ResponseBody): Boolean {
        val fielName = url.substring(url.lastIndexOf("/"))
        Log.d("downloadfilename",fielName)
        val directory = Environment
            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path
        var inputStream: InputStream? = null
        var outputStream: OutputStream? = null
        try {
            val file = File(directory + fielName)
            if (file.exists()) {
                file.delete()
            }
            inputStream = responseBody.byteStream()
            outputStream = FileOutputStream(file)
            val byte = ByteArray(1024)
            var length = 0
            while (true) {
                length = inputStream.read(byte)
                if (length == -1) {
                    break
                }
                outputStream.write(byte, 0, length)
            }
            outputStream.flush()
            return true
        } catch (e: Exception) {
            return false
        } finally {
            inputStream?.close()
            outputStream?.close()
        }
    }

    override fun ondestory() {
        iDownloadView = null
    }

}