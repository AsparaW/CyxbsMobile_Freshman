package com.mredrock.cyxbs.freshman.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.view.adapter.PagerAdapter
import com.mredrock.cyxbs.freshman.view.fragment.RouteFragment
import com.mredrock.cyxbs.freshman.view.fragment.SceneFragment
import kotlinx.android.synthetic.main.freshman_activity_guide_cqupt.*

/**
 * 指路重邮
 */
class GuideCquptActivity : BaseActivity() {
    override val isFragmentActivity: Boolean
        get() = true

    lateinit var vpAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_guide_cqupt)
        common_toolbar.init("入学流程")
        initFragments()
        tl_guide.setupWithViewPager(vp_guide)

    }

    fun initFragments() {
        val list = ArrayList<Fragment>()
        val fm = supportFragmentManager
        list.add(RouteFragment())
        list.add(SceneFragment())
        vpAdapter = PagerAdapter(arrayOf("公交路线","校园风光"),fm,list)
        vp_guide.adapter = vpAdapter

    }
}
