package com.mredrock.cyxbs.freshman.service

import com.mredrock.cyxbs.freshman.bean.CommunicateBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by tk on 2019/8/11
 */
interface CommunicateService {

    //学院和老乡公有service
    @GET
    fun getCommunicateObservable(@Url url: String) : Observable<CommunicateBean>
}