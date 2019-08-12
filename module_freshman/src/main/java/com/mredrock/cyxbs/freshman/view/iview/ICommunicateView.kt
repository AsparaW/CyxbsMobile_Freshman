package com.mredrock.cyxbs.freshman.view.iview

import com.mredrock.cyxbs.freshman.bean.CommunicateBean

/**
 * Created by tk on 2019/8/11
 */
 interface ICommunicateView {

    fun showContent( list: List<CommunicateBean.TextBean>)
}