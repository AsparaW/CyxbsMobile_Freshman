package com.mredrock.cyxbs.freshman.view.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.bean.SchoolItemBean

class ItemRecAdapter (val itemBean:SchoolItemBean):RecyclerView.Adapter<ItemRecAdapter.ViewHolder>() {
    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.freshman_item_school_out, parent, false)
        context=parent.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemBean.text?.size!!
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val str = itemBean.text?.get(position)?.title
        holder.textView.text = str
        val itemSubAdapter = itemBean.text?.get(position)?.let { ItemSubRecAdapter(it) }
        holder.recyclerView.adapter=itemSubAdapter
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        internal var recyclerView: RecyclerView
        internal var textView: TextView
        val layoutManager:LinearLayoutManager
        init {
            recyclerView = itemView.findViewById(R.id.freshman_out_rv)
            textView = itemView.findViewById(R.id.freshman_out_tv)
            layoutManager= LinearLayoutManager(itemView.getContext())
            layoutManager.orientation = RecyclerView.VERTICAL
            recyclerView.setLayoutManager(layoutManager)

        }
    }
    class ItemSubRecAdapter(val itemBean: SchoolItemBean.TextBean):RecyclerView.Adapter<ItemSubRecAdapter.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.freshman_item_schoolitem, parent, false)

            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return itemBean.data?.size!!
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val str = itemBean.data?.get(position)?.name
            holder.textView.text=str
        }

        class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
             var textView : TextView

            init{
                textView=itemView.findViewById(R.id.freshman_schoolitem_name)

            }
        }
    }
}
