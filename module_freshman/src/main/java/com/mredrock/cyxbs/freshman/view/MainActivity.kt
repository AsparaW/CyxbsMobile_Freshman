package com.mredrock.cyxbs.freshman.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.component.start
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.freshman.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.freshman_popwindow_download.view.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {
    override val isFragmentActivity: Boolean
        get() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        button.setOnClickListener{
//            show()
//        }


    }
    fun show(){
        val contentView = LayoutInflater.from(BaseApp.context).inflate(R.layout.freshman_popwindow_download, null)
        val rootView = LayoutInflater.from(BaseApp.context).inflate(R.layout.activity_main, null)
        val popupWindow = PopupWindow(
            contentView,
            (resources.displayMetrics.widthPixels * 0.95).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        popupWindow.isFocusable = true
        popupWindow.animationStyle = R.style.PopWindow
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 50)

        contentView.bt_download.setOnClickListener {
            //showingUrl.let { it1 -> presenter.download(it1) }
            toast("开始下载")
        }
        contentView.bt_down_cancle.setOnClickListener {
            popupWindow.dismiss()
        }

    }
}
