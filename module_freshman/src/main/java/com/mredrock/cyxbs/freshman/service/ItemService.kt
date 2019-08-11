package com.mredrock.cyxbs.freshman.service


import com.mredrock.cyxbs.freshman.bean.SchoolItemBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface ItemService {
    @GET
    fun getItemBean(@Url url: String): Observable<SchoolItemBean>
}