package com.mredrock.cyxbs.freshman.service

import retrofit2.http.Url
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET



/**
 * Created by tk on 2019/8/9
 */
interface DownloadService {
    /**
     * @para下载地址
     */
    @GET
    fun getReponse(@Url rl: String): Call<ResponseBody>
}