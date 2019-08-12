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


    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.iv_scence.setImageFromUrl(list[position].photo)
        holder.itemView.tv_scence_name.text = list[position].name
        holder.itemView.iv_scence.setOnClickListener {
            //查看大图
            context?.startActivity<BrowsePicsActivity>("photoes" to list.map { it.photo }, "pos" to holder.adapterPosition)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}