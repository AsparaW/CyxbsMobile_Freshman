package com.mredrock.cyxbs.freshman.presenter

import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.bean.AdminProBean
import com.mredrock.cyxbs.freshman.model.ProcessModel
import com.mredrock.cyxbs.freshman.view.iview.IAdminProView

/**
 * Created by tk on 2019/8/8
 */
class AdminProPresenter(var iAdminProView: IAdminProView?) : BasePresenter {
    val url: String = "http://129.28.185.138:9025/zsqy/json/2"

    val processModel = ProcessModel(url, object : OnloadModelListener {
        override fun <T> success(t: T) {
            val adminProBean = t as AdminProBean
            iAdminProView?.showProcess(adminProBean)
        }

        override fun failed() {
            LogUtils.d("onAdminPropresenter", "listtener failed")
        }
    })

    fun loadProcess() {
        //iAdminProView?.showDialog()
        processModel.getProcess()

    }

    override fun ondestory() {
        processModel.destory()
        this.iAdminProView = null
    }

}