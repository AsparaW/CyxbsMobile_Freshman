package com.mredrock.cyxbs.freshman.view.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.utils.extensions.setImageFromUrl
import com.mredrock.cyxbs.freshman.bean.ActivityBean
import com.mredrock.cyxbs.freshman.R
import kotlinx.android.synthetic.main.freshman_dialog_qr_code.view.*
import kotlinx.android.synthetic.main.freshman_recycle_item_online_activity.view.*

/**
 * Created by tk on 2019/8/11
 */
class ActivityRecycleAdapter(val list: List<ActivityBean.TextBean>,val listener: OnItemClickListener) : RecyclerView.Adapter<ActivityRecycleAdapter.ViewHolder>(){

    interface OnItemClickListener{
        fun onItemClick(photo: String?,message: String?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.freshman_recycle_item_online_activity,parent,false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.iv_activity_photo.setImageFromUrl(list[position].photo)
        holder.itemView.tv_activity_name.text = list[position].name
        holder.itemView.bt_join.setOnClickListener {

//            val mView = LayoutInflater.from(context)
//                .inflate(R.layout.freshman_dialog_qr_code,null)
//            mView.iv_qr_code.setImageFromUrl(list[position].photo)
//            mView.tv_activity_message.text = list[position].message
//            val dialog = AlertDialog.Builder(context).setView(mView).show()
//            dialog.view =
//            mView.iv_qr_code.setOnLongClickListener{
            listener.onItemClick(list[position].photo,list[position].message)
//
//            }
        }
    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}