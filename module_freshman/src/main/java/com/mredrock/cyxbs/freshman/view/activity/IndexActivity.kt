package com.mredrock.cyxbs.freshman.view.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.adapter.IndexRecycleAdapter
import kotlinx.android.synthetic.main.freshman_activity_main.*

class IndexActivity: BaseActivity(),View.OnClickListener {
    override fun onClick(v: View?) {
        if (v==freshman_show_button){
            LogUtils.d("1","AAAAAAAAAA")
            dismissWelcome()
        }
    }

    fun showWelcome() {
        freshman_show_button.visibility = View.VISIBLE
        freshman_show_image.visibility =View.VISIBLE
        freshman_showtext1.visibility =View.VISIBLE
        freshman_showtext2.visibility =View.VISIBLE
        freshman_showtext3.visibility =View.VISIBLE
        freshman_showtext4.visibility =View.VISIBLE
    }
    fun dismissWelcome(){
        freshman_show_button.visibility = View.INVISIBLE
        freshman_show_image.visibility =View.INVISIBLE
        freshman_showtext1.visibility =View.INVISIBLE
        freshman_showtext2.visibility =View.INVISIBLE
        freshman_showtext3.visibility =View.INVISIBLE
        freshman_showtext4.visibility =View.INVISIBLE

    }
    override val isFragmentActivity: Boolean
        get() = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_main)
        dismissWelcome()
        initRecView()
        freshman_show_button.setOnClickListener(this)
        val shard = getSharedPreferences("fresh_config", Context.MODE_PRIVATE)
        val editor = shard.edit()
        if (shard.getInt("isShow",0)==0){
            showWelcome()
            editor.putInt("isShow",1)
            editor.apply()
        }

    }
       fun initRecView() {
        val adapter = IndexRecycleAdapter(this)
        val layoutmanger = LinearLayoutManager(this)
        rv_index.adapter=adapter
        rv_index.layoutManager = layoutmanger
    }


}
