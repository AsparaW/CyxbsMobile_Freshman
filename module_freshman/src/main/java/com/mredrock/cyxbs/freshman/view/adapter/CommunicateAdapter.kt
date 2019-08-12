package com.mredrock.cyxbs.freshman.view.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.freshman.bean.CommunicateBean
import com.mredrock.cyxbs.freshman.R
import kotlinx.android.synthetic.main.freshman_dialog_whether_copy.view.*
import kotlinx.android.synthetic.main.freshman_recycle_item_content.view.*

/**
 * Created by tk on 2019/8/10
 */

class CommunicateAdapter(val list: List<CommunicateBean.TextBean>, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val FOOTER = 0
    val NORMAL = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType){
            NORMAL ->
                NormalViewHolder(
                    LayoutInflater.from(parent.context)
                    .inflate(R.layout.freshman_recycle_item_content,parent,false)
                )
            else->
                FooterViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.freshman_recycle_item_no_more,parent,false)
                )
        }



    //底部footer
    override fun getItemCount(): Int = list.size+1

    override fun getItemViewType(position: Int): Int =
        when(position){
            list.size -> FOOTER
            else -> NORMAL
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder.itemViewType == NORMAL) {
            holder.itemView.tv_name.text = list[position].name
            holder.itemView.setOnClickListener {
                val view = View.inflate(context, R.layout.freshman_dialog_whether_copy, null)
                view.tv_content_name.text = list[position].name
                view.tv_qq.text = "QQ群：${list[position].data}"
                val builder = AlertDialog.Builder(context)
                val dialog = builder.setView(view).setCancelable(true).show()

                view.tv_cancle.setOnClickListener { dialog.dismiss() }
                view.tv_sure.setOnClickListener {
                    dialog.dismiss()
                    //复制到剪贴板
                    val cbm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    cbm.primaryClip = ClipData.newPlainText(null, list[position].data)
                    AlertDialog.Builder(context)
                        .setView(R.layout.freshman_dialog_copy_success)
                        .setCancelable(true).show()
                }
            }
        }
    }

    class NormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}