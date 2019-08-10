package com.mredrock.cyxbs.freshman.view.iview

import com.mredrock.cyxbs.freshman.bean.SceneBean

/**
 * Created by tk on 2019/8/9
 */
interface ISceneView {
    fun showMap(title: String?,photo: String?)
    fun showScenes(list: List<SceneBean.TextBean.MessageBean>)
}