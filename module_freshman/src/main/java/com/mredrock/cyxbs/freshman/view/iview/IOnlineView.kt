package com.mredrock.cyxbs.freshman.view.iview

import com.mredrock.cyxbs.freshman.bean.ActivityBean

/**
 * Created by tk on 2019/8/11
 */
interface IOnlineView {

    fun showActivities(list: List<ActivityBean.TextBean>)
}