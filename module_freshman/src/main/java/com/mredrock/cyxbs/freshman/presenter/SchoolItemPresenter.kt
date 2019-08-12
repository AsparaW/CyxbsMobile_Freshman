package com.mredrock.cyxbs.freshman.presenter

import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.bean.SchoolItemBean
import com.mredrock.cyxbs.freshman.model.SchoolItemModel
import com.mredrock.cyxbs.freshman.view.iview.IItemView


class SchoolItemPresenter (var iItemView: IItemView?) : BasePresenter {
    val url = "http://129.28.185.138:8080/zsqy/json/1"

    val listener = object : OnloadModelListener {
        override fun <T> success(t: T) {
            val bean = t as SchoolItemBean
            LogUtils.d("onScenePresenter", "listener OK")
            iItemView?.showItem(bean)

        }

        override fun failed() {
            LogUtils.d("onScenePresenter", "listener failed")
        }
    }
    val schoolModel = SchoolItemModel(url,listener)
    fun loadItem() {
        //iAdminProView?.showDialog()
        schoolModel.getItem()
        LogUtils.e("11","Item")
    }

    override fun ondestory() {

    }

}
