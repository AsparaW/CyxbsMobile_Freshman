package com.mredrock.cyxbs.freshman.view.iview

import com.mredrock.cyxbs.freshman.bean.AdminProBean

/**
 * Created by tk on 2019/8/8
 */
interface IAdminProView {
    fun showDialog()
    fun DismissDialog()
    fun showProcess(adminProBean: AdminProBean)
}