package com.mredrock.cyxbs.freshman.view.activity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.freshman.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    override val isFragmentActivity: Boolean
        get() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        zidiyi.setData("传媒","10%","10%")


    }
}

