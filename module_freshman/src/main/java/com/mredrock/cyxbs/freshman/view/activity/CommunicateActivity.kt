package com.mredrock.cyxbs.freshman.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.adapter.PagerAdapter
import com.mredrock.cyxbs.freshman.view.fragment.CollegeFragment
import com.mredrock.cyxbs.freshman.view.fragment.ProvinceFragment
import com.mredrock.cyxbs.freshman.view.fragment.OnlineActivityFragment
import kotlinx.android.synthetic.main.freshman_activity_communicate.*


class CommunicateActivity : BaseActivity() {
    override val isFragmentActivity: Boolean
        get() = false

    lateinit var mAdapter: PagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_communicate)
        common_toolbar.init("线上交流")
        initFragment()
        tl_communicate.setupWithViewPager(vp_communicate)


    }

    fun initFragment() {
        val list = ArrayList<Fragment>()
        val fm = supportFragmentManager
        list.apply {
            add(CollegeFragment())
            add(ProvinceFragment())
            add(OnlineActivityFragment())
        }
        mAdapter = PagerAdapter(arrayOf("学院群", "老乡群", "线上交流"), fm, list)
        vp_communicate.adapter = mAdapter
    }


}
