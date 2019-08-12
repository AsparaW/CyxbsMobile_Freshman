package com.mredrock.cyxbs.freshman.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.utils.extensions.setImageFromUrl
import com.mredrock.cyxbs.freshman.url.IMAGE


/**
 * Created by tk on 2019/8/6
 * 校园风光fargment重邮景点展示viewpager适配器
 */
class SceneVpAdapter(val list: ArrayList<String>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = list.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(BaseApp.context)
        imageView.setImageFromUrl("${IMAGE}${list[position]}")
        container.addView(imageView)
        return imageView

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
