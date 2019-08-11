package com.mredrock.cyxbs.freshman.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.freshman.R

class SchoolItemActivity :BaseActivity(),View.OnClickListener {
     override val isFragmentActivity: Boolean
         get() = false

     override fun onClick(v: View?) {
         TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
     }

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_school_item)
    }
}
