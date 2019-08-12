package com.mredrock.cyxbs.freshman.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.activity.*


class IndexRecycleAdapter(val activity: IndexActivity) :

    RecyclerView.Adapter<IndexRecycleAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null
lateinit var context :Context

init {

}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.freshman_item_sy, parent, false)
        context=parent.context
        return ViewHolder(view)
    }

    /**
     * goto
     */
    fun goto(clazz:Class<Any>){
        val intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setClass(context,clazz)
        context.startActivity(intent)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            if (position == 0) {
                holder.textView1.text = "入学必备"
                holder.textView2.text = "报道必备、宿舍用品、学习用具"
            } else if (position == 1) {
                holder.textView1.text = "指路重邮"
                holder.textView2.text = "重邮线路、重邮地图"
            } else if (position == 2) {
                holder.textView1.text = "入学流程"
                holder.textView2.text = "入学步骤、入学地点"
            } else if (position == 3) {
                holder.textView1.text = "新生课表"
                holder.textView2.text = " "

            } else if (position == 4) {
                holder.textView1.text = "校园指引"
                holder.textView2.text = "宿舍、快递地点指引"
            } else if (position == 5) {
                holder.textView1.text = "线上交流"
                holder.textView2.text = "老乡群、专业群"
            } else if (position == 6) {
                holder.textView1.text = "更多功能"
                holder.textView2.text = "迎新网、新生课表"
            } else if (position == 7) {
                holder.textView1.text = "关于我们"
                holder.textView2.text = " "
            } else if (position == 8) {
                holder.textView1.text = "我想对你说"
                holder.textView2.text = " "
            }

        holder.itemView.setOnClickListener {

                LogUtils.e("LLLLLLLL",position.toString())

                if (position == 0) {
//                    holder.textView1.text = "入学必备"
//                    holder.textView2.text = "报道必备、宿舍用品、学习用具"
                    goto(SchoolItemActivity().javaClass)
                } else if (position == 1) {
//                    holder.textView1.text = "指路重邮"
//                    holder.textView2.text = "重邮线路、重邮地图"
                    goto(GuideCquptActivity().javaClass)
                } else if (position == 2) {
//                    holder.textView1.text = "入学流程"
//                    holder.textView2.text = "入学步骤、入学地点"
                    goto(AdmissionProcessActivity().javaClass)
                    LogUtils.d("1","1")
                } else if (position == 3) {
//                    holder.textView1.text = "新生课表"
//                    holder.textView2.text = " "


                } else if (position == 4) {
//                    holder.textView1.text = "校园指引"
//                    holder.textView2.text = "宿舍、快递地点指引"

                } else if (position== 5) {
//                    holder.textView1.text = "线上交流"
//                    holder.textView2.text = "老乡群、专业群"
                    goto(CommunicateActivity().javaClass)
                } else if (position== 6) {
//                    holder.textView1.text = "更多功能"
//                    holder.textView2.text = "迎新网、新生课表"
                    goto(MoreFunctionActivity().javaClass)
                } else if (position == 7) {
//                    holder.textView1.text = "关于我们"
//                    holder.textView2.text = " "
                } else if (position== 8) {
//                    holder.textView1.text = "我想对你说"
//                    holder.textView2.text = "
                    //
                    activity.showWelcome()
                }
            }
    }



    override fun getItemCount(): Int {
        return 9
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var textView1: TextView
        internal var textView2: TextView

        init {
            textView1 = itemView.findViewById(R.id.freshman_textview)
            textView2 = itemView.findViewById(R.id.freshman_textview2)
        }
    }

    interface OnItemClickListener {
        fun onItemContentClick(view: View, position: Int)
    }
}
