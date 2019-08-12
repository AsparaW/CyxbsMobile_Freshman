package com.mredrock.cyxbs.freshman.model

import com.mredrock.cyxbs.common.network.ApiGenerator
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.freshman.presenter.OnloadModelListener
import com.mredrock.cyxbs.freshman.service.CommunicateService
import io.reactivex.disposables.Disposable


/**
 * Created by tk on 2019/8/11
 */
class CommunicateModel(val listener: OnloadModelListener) : BaseModel{
    lateinit var disposable: Disposable
    fun getResult(url: String) {
        val mService = ApiGenerator.getApiService(CommunicateService::class.java)
        disposable = mService.getCommunicateObservable(url)
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