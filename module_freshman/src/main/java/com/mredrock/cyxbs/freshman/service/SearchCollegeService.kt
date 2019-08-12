package com.mredrock.cyxbs.freshman.service

import com.mredrock.cyxbs.freshman.bean.CommunicateBean
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by tk on 2019/8/10
 */
interface SearchCollegeService {

     @POST
     @FormUrlEncoded
     fun getSearchResultObservable(@Url url: String,@Field("college") college: String) : Observable<CommunicateBean>

}