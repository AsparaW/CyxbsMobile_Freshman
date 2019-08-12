package com.mredrock.cyxbs.freshman.presenter

import android.util.Log
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.bean.SceneBean
import com.mredrock.cyxbs.freshman.model.SceneModel
import com.mredrock.cyxbs.freshman.view.iview.ISceneView

/**
 * Created by tk on 2019/8/9
 */
class ScenePresenter(var iSceneView: ISceneView?) : BasePresenter {

    val listener = object : OnloadModelListener {
        override fun <T> success(t: T) {
            val sceneBean = t as SceneBean
            Log.d("testrequest",sceneBean.info)
            iSceneView?.showMap(sceneBean.text?.title, sceneBean.text?.photo)
            //测试数据
            //iSceneView?.showMap("重邮地图","http://ww1.sinaimg.cn/large/006nwaiFly1g2lw2ys8r9j31z4140grd.jpg")

            sceneBean.text?.message?.let { iSceneView?.showScenes(it) }
        }

        override fun failed() {
            LogUtils.d("onScenePresenter", "listtener failed")
        }
    }

    val sceneModel = SceneModel(listener)

    fun loadScene(){
        sceneModel.getScene()
    }
    override fun ondestory() {
        sceneModel.destory()
        iSceneView = null
    }


}