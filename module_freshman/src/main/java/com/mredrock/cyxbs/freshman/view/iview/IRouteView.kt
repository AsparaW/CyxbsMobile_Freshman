package com.mredrock.cyxbs.freshman.view.iview

import com.mredrock.cyxbs.freshman.bean.ColorfulRouteBean
import com.mredrock.cyxbs.freshman.bean.RouteBean


interface IRouteView {

    fun showRoutes(title: String,list: List<ColorfulRouteBean>)

    fun showCquptAdd(text1Bean: RouteBean.Text1Bean)


}