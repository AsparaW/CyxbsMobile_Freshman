package com.mredrock.cyxbs.freshman

import android.app.Activity
import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import android.view.WindowManager






/**
 * Created by tk on 2019/8/11
 */
/**
 * 获取屏幕宽度
 * @return 屏幕宽度
 */
fun AppCompatActivity.getScreenWidth() = resources.displayMetrics.widthPixels

/**
 * 降低显示透明度
 */
fun Activity.reduceTransparency(){

    val lp = window.getAttributes()

    lp.alpha = 0.8f //设置透明度
    window.setAttributes(lp)
    window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
}

/**
 * 还原透明度
 */
fun Activity.resetTransparency(){
    val lp1 = window.attributes
    lp1.alpha = 1f
    window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    window.attributes = lp1
}

fun Context.dpToPx(dp: Float) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,getResources().getDisplayMetrics())
