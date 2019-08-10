package com.mredrock.cyxbs.freshman.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.BaseApp.Companion.context
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.activity.MoreFunctionActivity
import kotlinx.android.synthetic.main.freshman_item_more.view.*

class MoreFunctionAdapter (val activity:MoreFunctionActivity): RecyclerView.Adapter<MoreFunctionAdapter.ViewHolder>() {
    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.freshman_item_more, parent, false)
        context=parent.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        LogUtils.e("1",position.toString())
        if (position == 0) {
            holder.textView1.text = "重邮2019迎新专题"
            holder.textView2.text = "邮你造未来"
        } else if (position == 1) {
            holder.textView1.text = "掌上重邮新功能"
            holder.textView2.text = "校车，校历等更多查询功能"
        } else if (position == 2) {
            holder.textView1.text = "重邮小帮手"
            holder.textView2.text = "学长学姐帮帮忙"
        }
        holder.itemView.setOnClickListener {


            if (position == 0) {
//                holder.textView1.text = "重邮2019迎新专题"
//                holder.textView2.text = "邮你造未来"
            } else if (position == 1) {
//                holder.textView1.text = "掌上重邮新功能"
//                holder.textView2.text = "校车，校历等更多查询功能"
            } else if (position == 2) {

//                holder.textView1.text = "重邮小帮手"
//                holder.textView2.text = "学长学姐帮帮忙"
                activity.showQR()
            }
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var textView1: TextView
        internal var textView2: TextView

        init {
            textView1 = itemView.findViewById(R.id.freshman_more_tv1)
            textView2 = itemView.findViewById(R.id.freshman_more_tv2)
        }
    }

}