package com.mredrock.cyxbs.freshman.model

import com.mredrock.cyxbs.common.network.ApiGenerator
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers

import com.mredrock.cyxbs.freshman.presenter.OnloadModelListener
import com.mredrock.cyxbs.freshman.service.ItemService

import io.reactivex.disposables.Disposable

class SchoolItemModel(val url: String, val listener: OnloadModelListener) : BaseModel{

    lateinit var disposable: Disposable
    fun getItem() {
        val itemService = ApiGenerator.getApiService(ItemService::class.java)
        disposable = itemService.getItemBean(url)
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