package com.mredrock.cyxbs.freshman.presenter

import com.mredrock.cyxbs.freshman.bean.ActivityBean
import com.mredrock.cyxbs.freshman.model.ActivityModel
import com.mredrock.cyxbs.freshman.view.iview.IOnlineView

/**
 * Created by tk on 2019/8/11
 */
class OnlineActivityPresenter(var iOnlineView: IOnlineView?) : BasePresenter{

    var mModel = ActivityModel(object : OnloadModelListener{
        override fun <T> success(t: T) {
            val mBean = t as ActivityBean
            mBean.text?.let { iOnlineView?.showActivities(it) }
        }

        override fun failed() {

        }
    })

    fun loadActivities(){
        mModel.getActivity()
    }
    override fun ondestory() {
        iOnlineView = null
        mModel.destory()
    }
}