package com.mredrock.cyxbs.freshman.presenter

/**
 * Created by tk on 2019/8/8
 */
interface OnloadModelListener {
    fun <T> success(t:T)
    fun failed()
}