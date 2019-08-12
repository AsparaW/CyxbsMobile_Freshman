package com.mredrock.cyxbs.freshman.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.bean.SchoolItemBean
import com.mredrock.cyxbs.freshman.presenter.SchoolItemPresenter
import com.mredrock.cyxbs.freshman.view.adapter.ItemRecAdapter
import com.mredrock.cyxbs.freshman.view.iview.IItemView
import kotlinx.android.synthetic.main.freshman_item_school_out.*

class SchoolItemActivity :BaseActivity(),View.OnClickListener,IItemView {
    override fun showItem(bean: SchoolItemBean) {
        LogUtils.e("","RECITEM")
        adapter = ItemRecAdapter(bean)
        val layoutmanger = LinearLayoutManager(this)
        freshman_out_rv.adapter = adapter
        freshman_out_rv.layoutManager = layoutmanger
        LogUtils.e("","RECITEM")
    }

    override fun addMyItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteMyItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var adapter: ItemRecAdapter
    lateinit var presenter: SchoolItemPresenter

     override val isFragmentActivity: Boolean
         get() = false

     override fun onClick(v: View?) {
         TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
     }

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_school_item)
         common_toolbar.init("入学必备")
         presenter = SchoolItemPresenter(this)
         presenter.loadItem()
    }
}
