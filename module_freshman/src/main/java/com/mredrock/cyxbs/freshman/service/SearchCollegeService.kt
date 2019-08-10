package com.mredrock.cyxbs.freshman.service

import com.mredrock.cyxbs.freshman.bean.CollegeNumBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * Created by tk on 2019/8/10
 */
interface SearchCollegeService {

    @FormUrlEncoded
    @POST
    fun getCollegeNum(@Url url: String,@Field("college") college: String) : Observable<CollegeNumBean>
}