package com.mredrock.cyxbs.freshman.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.component.PhotoViewerActivity
import com.mredrock.cyxbs.common.utils.extensions.setAvatarImageFromUrl
import com.mredrock.cyxbs.common.utils.extensions.setImageFromUrl
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.bean.SceneBean
import com.mredrock.cyxbs.freshman.view.activity.BrowsePicsActivity
import kotlinx.android.synthetic.main.freshman_recycle_item_photo.view.*
import org.jetbrains.anko.startActivity

class SceneRecycleAdapter(val list: List<SceneBean.TextBean.MessageBean>, val context: Context?) :
    RecyclerView.Adapter<SceneRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.freshman_recycle_item_photo, parent, false)
        )


    override fun getItemCount(): Int = 10


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.iv_scence.setImageFromUrl("http://ww1.sinaimg.cn/large/006nwaiFly1g2lw2ys8r9j31z4140grd.jpg")
        holder.itemView.tv_scence_name.text = "测试"
        holder.itemView.iv_scence.setOnClickListener {
            //测试数据
            //val urlList = list.map { it.photo }
//            val urlList = ArrayList<String>()
//            for(i in 1..10){
//                urlList.add("http://ww1.sinaimg.cn/large/006nwaiFly1g2lw2ys8r9j31z4140grd.jpg")
//            }
            context?.startActivity<BrowsePicsActivity>("photoes" to list, "pos" to holder.adapterPosition)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}