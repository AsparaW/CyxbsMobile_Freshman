package com.mredrock.cyxbs.freshman.view.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.utils.extensions.gone
import com.mredrock.cyxbs.common.utils.extensions.setImageFromUrl
import com.mredrock.cyxbs.common.utils.extensions.visible
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.bean.AdminProBean
import com.mredrock.cyxbs.freshman.url.IMAGE
import kotlinx.android.synthetic.main.freshman_recycle_item_process.view.*
import kotlinx.android.synthetic.main.freshman_recycle_item_route_two.view.*
import org.jetbrains.anko.backgroundResource

/**
 * Created by tk on 2019/8/8
 */
class AdminProRecycleAdapter(val adminProBean: AdminProBean) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TIME = 0 //顶部的报道时间同样作为一个item
    val PROCESS = 1
    var openedPosition: Int = -1//记录哪个item展开了
    val textbeanList = adminProBean.text
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =

        when (viewType) {
            TIME -> TimeViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.freshman_recycle_item_top_time, parent, false)
            )
            else -> ProcessViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.freshman_recycle_item_process, parent, false)
            )
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val textBean = textbeanList?.get(position)
        when (holder.itemViewType) {
            TIME -> {
                val timeViewHolder = holder as TimeViewHolder
                timeViewHolder.time.text = "${textBean?.title} : ${textBean?.message}"
            }
            else -> {
                holder.itemView.tv_message.text = textBean?.title
                holder.itemView.tv_detail.text = textBean?.detail
                holder.itemView.iv_photo.setImageFromUrl("${IMAGE}${textBean?.photo}")
                if (openedPosition == position) {
                    holder.itemView.iv_more_process.backgroundResource = R.drawable.freshman_ic_expand_less_black_24dp
                    holder.itemView.tv_detail.visible()
                    holder.itemView.iv_photo.visible()
                } else {
                    holder.itemView.iv_more_process.backgroundResource = R.drawable.freshman_ic_expand_more_black_24dp
                    holder.itemView.tv_detail.gone()
                    holder.itemView.iv_photo.gone()
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (TextUtils.isEmpty(textbeanList?.get(position)?.detail)) TIME else PROCESS

    override fun getItemCount(): Int = textbeanList?.size!!

    inner class ProcessViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                if (openedPosition == getAdapterPosition()) {
                    //当点击的item已经被展开了, 就关闭.
                    openedPosition = -1;

                    notifyItemChanged(getAdapterPosition())
                } else {
                    val oldOpenedposition = openedPosition
                    openedPosition = getAdapterPosition()

                    notifyItemChanged(oldOpenedposition)
                    notifyItemChanged(openedPosition)
                }
            }
        }
    }

    inner class TimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var time: TextView

        init {
            time = itemView as TextView
        }
    }

}
