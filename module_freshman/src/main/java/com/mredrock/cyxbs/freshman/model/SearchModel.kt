package com.mredrock.cyxbs.freshman.model

import com.mredrock.cyxbs.common.network.ApiGenerator
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.freshman.service.SearchCollegeService
import com.mredrock.cyxbs.freshman.service.SearchProvinceService
import com.mredrock.cyxbs.freshman.url.SEARCH_COLLEGE
import com.mredrock.cyxbs.freshman.url.SEARCH_PROVINCE

/**
 * Created by tk on 2019/8/10
 */
class SeacrchModel : BaseModel {

    val mCollegeService by lazy { ApiGenerator.getApiService(SearchCollegeService::class.java) }
    val mProvinceService by lazy { ApiGenerator.getApiService(SearchProvinceService::class.java) }
    //lateinit var observable : Observable<CommunicateBean>
//            by lazy { mService.getResult(url, value).setSchedulers() }

    fun getCollegeObservable( value: String) =
        mCollegeService.getSearchResultObservable(SEARCH_COLLEGE, value).setSchedulers()

    fun getProvinceObservable(value: String) =
          mProvinceService.getSearchResultObservable(SEARCH_PROVINCE,value).setSchedulers()
//    fun getContent() {
//        disposable = observable.subscribe({
//            if (it != null) {
//                listener.success(it)
//            } else {
//                listener.failed()
//            }
//        }, {
//            listener.failed()
//        })
//
//
//    }

            override

    fun destory() {

    }
}