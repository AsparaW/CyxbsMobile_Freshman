package com.mredrock.cyxbs.freshman.model

import com.mredrock.cyxbs.common.network.ApiGenerator
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.freshman.presenter.OnloadModelListener
import com.mredrock.cyxbs.freshman.service.ActivityService
import com.mredrock.cyxbs.freshman.service.RouteService
import com.mredrock.cyxbs.freshman.url.ONLINE_ACTIVITY
import io.reactivex.disposables.Disposable

/**
 * Created by tk on 2019/8/11
 */
class ActivityModel(val listener : OnloadModelListener) : BaseModel {
    lateinit var disposable: Disposable
    fun getActivity() {
        val mService = ApiGenerator.getApiService(ActivityService::class.java)
        disposable = mService.getActivityObservabe(ONLINE_ACTIVITY)
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
        disposable.dispose()
    }
}



