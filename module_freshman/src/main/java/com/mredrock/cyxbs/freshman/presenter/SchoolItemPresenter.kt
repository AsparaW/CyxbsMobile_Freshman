package com.mredrock.cyxbs.freshman.presenter

import com.mredrock.cyxbs.freshman.bean.SchoolItemBean
import com.mredrock.cyxbs.freshman.view.iview.IItemView


class SchoolItemPresenter (var iItemView: IItemView?) : BasePresenter {
    val url = "http://129.28.185.138:9025/zsqy/json/1"
    val listener = object : OnloadModelListener {
        override fun <T> success(t: T) {
            val itemBean = t as SchoolItemBean
            iItemView?.showItem(itemBean)

        }

        override fun failed() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }


    }

    override fun ondestory() {

    }

}
