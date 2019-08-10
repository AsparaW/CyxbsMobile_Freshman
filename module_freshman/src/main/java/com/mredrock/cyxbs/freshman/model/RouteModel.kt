package com.mredrock.cyxbs.freshman.model

import com.mredrock.cyxbs.common.network.ApiGenerator
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.freshman.presenter.OnloadModelListener
import com.mredrock.cyxbs.freshman.service.ProcessService
import com.mredrock.cyxbs.freshman.service.RouteService
import io.reactivex.disposables.Disposable
import retrofit2.http.Url

/**
 * Created by tk on 2019/8/9
 */
class RouteModel(val url: String,val listener: OnloadModelListener):BaseModel {

    lateinit var disposable: Disposable
    fun getRoute() {
        val processService = ApiGenerator.getApiService(RouteService::class.java)
        disposable = processService.getRouteBean(url)
            .setSchedulers()
            .subscribe({
                if (it != null) {
                    listener.success(it)
                } else {
                    listener.failed()
                }
            }, {
                listener.failed()
            })


    }

    override fun destory() {
        if (!disposable.isDisposed){
            disposable.dispose()
        }
    }
}