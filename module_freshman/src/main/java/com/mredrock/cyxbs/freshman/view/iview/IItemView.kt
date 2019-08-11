package com.mredrock.cyxbs.freshman.view.iview

import com.mredrock.cyxbs.freshman.bean.SchoolItemBean

interface IItemView {
    fun showItem(bean:SchoolItemBean)
    fun addMyItem()
    fun deleteMyItem()
}