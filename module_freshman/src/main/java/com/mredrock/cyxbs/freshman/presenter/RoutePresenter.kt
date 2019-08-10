package com.mredrock.cyxbs.freshman.presenter

import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.bean.ColorfulRoute
import com.mredrock.cyxbs.freshman.bean.ColorfulRouteBean
import com.mredrock.cyxbs.freshman.bean.RouteBean
import com.mredrock.cyxbs.freshman.model.RouteModel
import com.mredrock.cyxbs.freshman.view.iview.IRouteView

/**
 * Created by tk on 2019/8/9
 */
class RoutePresenter(var iRouteView: IRouteView?) : BasePresenter {
    val url: String = "http://129.28.185.138:9025/zsqy/json/5"
    var listener = object : OnloadModelListener {
        override fun <T> success(t: T) {
            val routeBean = t as RouteBean
            routeBean.text_1?.let { iRouteView?.showCquptAdd(it) }
            val list = ArrayList<ColorfulRouteBean>()
            routeBean.text_2?.message?.forEach {

                //根据第一次箭头和最后一次箭头拆出起点和终点
                val routelist = ArrayList<ColorfulRoute>()
                it.route?.forEach {
                    val firstIndex = it.indexOf("→")
                    val lastIndex = it.lastIndexOf("→")
                    routelist.add(ColorfulRoute(it.substring(0,firstIndex),it.substring(firstIndex,lastIndex+1),it.substring(lastIndex+1)))
                }
                list.add(ColorfulRouteBean(it.name,routelist))
            }
            routeBean.text_2?.title?.let { iRouteView?.showRoutes(it,list) }
        }

        override fun failed() {
            LogUtils.d("onRoutepresenter", "listtener failed")
        }

    }

    val routeModel = RouteModel(url, listener)

    fun loadRoute() {
        routeModel.getRoute()
    }


    override fun ondestory() {
        iRouteView = null
    }
}