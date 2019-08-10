package com.mredrock.cyxbs.freshman.view.adapter

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.utils.extensions.gone
import com.mredrock.cyxbs.common.utils.extensions.visible
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.bean.ColorfulRouteBean
import kotlinx.android.synthetic.main.freshman_recycle_item_route_two.view.*
import org.jetbrains.anko.backgroundResource

class RouteRecycleAdapter(val routeList: List<ColorfulRouteBean>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val ROUTE_ONE = 1;
    val ROUTE_TWO = 2
    var openedId = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ROUTE_ONE -> OneViewHoider(
                LayoutInflater.from(parent.context).inflate(R.layout.freshman_recycle_item_route_one, parent, false)
            )
            else ->
                TwoViewHoider(
                    LayoutInflater.from(parent.context).inflate(R.layout.freshman_recycle_item_route_two, parent, false)
                )
        }


    override fun getItemViewType(position: Int): Int = routeList[position].list.size

    override fun getItemCount(): Int = routeList.size

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val list = routeList[position].list
        when (holder.itemViewType) {
            ROUTE_ONE -> {
                holder.itemView.tv_route_name.text = routeList[position].name
                holder.itemView.tv_route_one.text = Html.fromHtml(
                    "<font color=\"#5b69ff\">${list[0].start}</font><font color=\"#666666\">${list[0].middle}</font><font color=\"#b573ff\">${list[0].end}</font>",
                    Html.FROM_HTML_MODE_LEGACY
                )
                if (openedId == position) {
                    holder.itemView.tv_route_name.visible()
                    holder.itemView.tv_route_tip_one.visible()
                    holder.itemView.tv_route_one.visible()
                    holder.itemView.iv_Arrow.backgroundResource = R.drawable.freshman_ic_expand_less_black_24dp
                } else {
                    holder.itemView.tv_route_one.gone()
                    holder.itemView.tv_route_tip_one.gone()
                    holder.itemView.iv_Arrow.backgroundResource = R.drawable.freshman_ic_expand_more_black_24dp
                }
            }
            else -> {
                holder.itemView.tv_route_name.text = routeList[position].name
                holder.itemView.tv_route_one.text = Html.fromHtml(
                    "<font color=\"#5b69ff\">${list[0].start}</font><font color=\"#666666\">${list[0].middle}</font><font color=\"#b573ff\">${list[0].end}</font>",Html.FROM_HTML_MODE_LEGACY)
                holder.itemView.tv_route_two.text = Html.fromHtml(
                    "<font color=\"#5b69ff\">${list[1].start}</font><font color=\"#666666\">${list[1].middle}</font><font color=\"#b573ff\">${list[1].end}</font>",Html.FROM_HTML_MODE_LEGACY)
                if (openedId == position){
                    holder.itemView.tv_route_tip_one.visible()
                    holder.itemView.tv_route_one.visible()
                    holder.itemView.tv_route_tip_two.visible()
                    holder.itemView.tv_route_two.visible()
                    holder.itemView.iv_Arrow.backgroundResource = R.drawable.freshman_ic_expand_less_black_24dp
                }else{
                    holder.itemView.tv_route_tip_one.gone()
                    holder.itemView.tv_route_one.gone()
                    holder.itemView.tv_route_tip_two.gone()
                    holder.itemView.tv_route_two.gone()
                    holder.itemView.iv_Arrow.backgroundResource = R.drawable.freshman_ic_expand_more_black_24dp
                }
            }
        }

        holder.itemView.setOnClickListener {
            if (openedId == holder.getAdapterPosition()) {
                //当点击的item已经被展开了, 就关闭.
                openedId = -1;
                notifyItemChanged(holder.getAdapterPosition())
            } else {
                val lastOpenedID = openedId
                openedId = holder.getAdapterPosition()
                notifyItemChanged(lastOpenedID)
                notifyItemChanged(openedId)
            }
        }
    }

    open inner class OneViewHoider(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class TwoViewHoider(itemView: View) : OneViewHoider(itemView)
}