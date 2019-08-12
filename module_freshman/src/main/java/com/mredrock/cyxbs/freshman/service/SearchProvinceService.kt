package com.mredrock.cyxbs.freshman.service

import com.mredrock.cyxbs.freshman.bean.CommunicateBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Url

/**
 * Created by tk on 2019/8/12
 */
interface SearchProvinceService {
    @POST
    @FormUrlEncoded
    fun getSearchResultObservable(@Url url: String, @Field("province") college: String) : Observable<CommunicateBean>
}