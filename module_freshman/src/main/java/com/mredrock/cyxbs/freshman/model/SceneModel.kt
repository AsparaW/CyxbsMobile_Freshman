package com.mredrock.cyxbs.freshman.model

import com.mredrock.cyxbs.common.network.ApiGenerator
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.freshman.presenter.OnloadModelListener
import com.mredrock.cyxbs.freshman.service.SceneService
import com.mredrock.cyxbs.freshman.url.SCHOOL_SCENE
import io.reactivex.disposables.Disposable

/**
 * Created by tk on 2019/8/9
 */
class SceneModel(val listener: OnloadModelListener) : BaseModel {

    lateinit var disposable: Disposable
    fun getScene() {
        val sceneService = ApiGenerator.getApiService(SceneService::class.java)
        disposable = sceneService.getSceneObservable(SCHOOL_SCENE)
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