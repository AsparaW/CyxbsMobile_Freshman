package com.mredrock.cyxbs.freshman.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.freshman.bean.CommunicateBean
import com.mredrock.cyxbs.freshman.R
import kotlinx.android.synthetic.main.freshman_recycle_item_content.view.*

/**
 * Created by tk on 2019/8/12
 */
class SearchRecycleAdapter(val list: List<CommunicateBean.TextBean>?,val context: Context,val listener:SearchRecycleAdapter.OnSearchResultClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val NORMAL = 1
    val NONE = 0

    interface OnSearchResultClickListener{
        fun onSearchItemClick(name: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType){
            NORMAL -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.freshman_recycle_item_content,parent,false))
            else -> NoneHolder(LayoutInflater.from(parent.context).inflate(R.layout.freshman_recycle_item_no_search_result,parent,false))
        }



    override fun getItemCount(): Int =
        list?.size?:1

    override fun getItemViewType(position: Int): Int =
        if (list.isNullOrEmpty()) NONE
        else NORMAL


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == NORMAL){
            holder.itemView.tv_name.text = list?.get(position)?.name
            holder.itemView.setOnClickListener{
                //点击滑动至搜索目标
                listener.onSearchItemClick(list?.get(position)?.name!!)
            }

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class NoneHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}