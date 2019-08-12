package com.mredrock.cyxbs.freshman.service


import com.mredrock.cyxbs.freshman.bean.ActivityBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by tk on 2019/8/11
 * 线上活动
 */
interface ActivityService {

    @GET
    fun getActivityObservabe(@Url url: String) : Observable<ActivityBean>

}