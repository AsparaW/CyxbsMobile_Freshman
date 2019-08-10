package com.mredrock.cyxbs.freshman.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.mredrock.cyxbs.common.network.ApiGenerator
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.common.utils.extensions.setSchedulers
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.service.SearchCollegeService
import org.jetbrains.anko.toast

class CommunicateActivity : BaseActivity() {
    override val isFragmentActivity: Boolean
        get() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_communicate)
        common_toolbar.init("线上交流")
        val searchCollegeService = ApiGenerator.getApiService(SearchCollegeService::class.java)
        searchCollegeService.getCollegeNum("http://129.28.185.138:9025/zsqy/select","计算机")
            .setSchedulers()
            .subscribe{
                it.text?.get(0)?.name?.let { it1 -> toast(it1) }
            }
    }
}
