package com.mredrock.cyxbs.freshman.model

import com.mredrock.cyxbs.common.network.ApiGenerator
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.freshman.presenter.OnloadModelListener
import com.mredrock.cyxbs.freshman.service.ProcessService
import io.reactivex.disposables.Disposable

/**
 * Created by tk on 2019/8/8
 */
class ProcessModel(val url: String, val listener: OnloadModelListener) : BaseModel{

    lateinit var disposable: Disposable
    fun getProcess() {
        val processService = ApiGenerator.getApiService(ProcessService::class.java)
        disposable = processService.getProcessBean(url)
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