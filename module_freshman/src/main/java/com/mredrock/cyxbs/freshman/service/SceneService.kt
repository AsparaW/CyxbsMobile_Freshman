package com.mredrock.cyxbs.freshman.service

import com.mredrock.cyxbs.freshman.bean.SceneBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url


/**
 * Created by tk on 2019/8/9
 */
interface SceneService {
    //入学流程
    @GET
    fun getSceneBean(@Url url: String): Observable<SceneBean>
}
