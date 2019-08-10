package com.mredrock.cyxbs.freshman.view.activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.common.utils.extensions.visible
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.adapter.MoreFunctionAdapter
import kotlinx.android.synthetic.main.freshman_activity_more.*

class MoreFunctionActivity : BaseActivity(),View.OnClickListener {
    override fun onClick(v: View?) {
        if (freshman_more_imageview.visibility==View.VISIBLE){
            dismissQR()
        }
        if(v==freshman_more_imageview){
            dismissQR()
        }
    }

    override val isFragmentActivity: Boolean
        get() = false

    fun showQR(){
        freshman_more_imageview.visibility=View.VISIBLE
    }
    fun dismissQR(){
        freshman_more_imageview.visibility=View.INVISIBLE
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_more)
        common_toolbar.init("更多功能")
        dismissQR()
        initRecView()
        freshman_more_layout.setOnClickListener(this)
        freshman_more_imageview.setOnClickListener(this)
    }
    fun initRecView() {
        val adapter = MoreFunctionAdapter(this)
        val layoutmanger = LinearLayoutManager(this)
        freshman_more_rv.adapter=adapter
        freshman_more_rv.layoutManager = layoutmanger
    }
}

