package com.mredrock.cyxbs.freshman.view.fragment

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.bean.ColorfulRouteBean
import com.mredrock.cyxbs.freshman.bean.RouteBean
import com.mredrock.cyxbs.freshman.presenter.RoutePresenter
import com.mredrock.cyxbs.freshman.view.adapter.RouteRecycleAdapter
import com.mredrock.cyxbs.freshman.view.iview.IRouteView
import kotlinx.android.synthetic.main.freshman_fragment_routes.*
import org.jetbrains.anko.support.v4.toast

/**
 * 公交线路fragment
 */
class RouteFragment : Fragment(), IRouteView {

    val presenter by lazy { RoutePresenter(this) }
    lateinit var adapter: RouteRecycleAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.freshman_fragment_routes, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.loadRoute()
        tv_copy.setOnClickListener {
            showCopyedDialog()
        }
    }

    override fun showRoutes(title: String, list: List<ColorfulRouteBean>) {
        tv_advise_route.text = title
        adapter = RouteRecycleAdapter(list)
        rv_advise_route.adapter = adapter
        val manger = LinearLayoutManager(activity)
        rv_advise_route.layoutManager = manger
    }


    override fun showCquptAdd(text1Bean: RouteBean.Text1Bean) {
        tv_cqupt.text = text1Bean.title
        tv_cqupt_add.text = text1Bean.message

    }

    fun showCopyedDialog() {
        //复制到剪贴板
        val cbm = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        cbm?.primaryClip = ClipData.newPlainText(null, tv_cqupt_add.text)

        toast("复制成功")
    }
}