package com.mredrock.cyxbs.freshman.service

import com.mredrock.cyxbs.freshman.bean.AdminProBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by tk on 2019/8/8
 */
interface BaseService<T> {
    //入学流程
    @GET
    fun getObservable(@Url url: String): Observable<T>
}