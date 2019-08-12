package com.mredrock.cyxbs.freshman.view.activity

import android.Manifest
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.viewpager.widget.ViewPager
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.common.utils.extensions.*
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.presenter.DownloadPresenter
import com.mredrock.cyxbs.freshman.reduceTransparency
import com.mredrock.cyxbs.freshman.resetTransparency
import com.mredrock.cyxbs.freshman.url.IMAGE
import com.mredrock.cyxbs.freshman.view.adapter.SceneVpAdapter
import com.mredrock.cyxbs.freshman.view.iview.IDownloadView
import kotlinx.android.synthetic.main.freshman_activity_browse_pics.*
import kotlinx.android.synthetic.main.freshman_popwindow_download.view.*
import org.jetbrains.anko.toast

/**
 * 重邮校园风光浏览大图
 */
class BrowsePicsActivity : BaseActivity(), View.OnLongClickListener, IDownloadView {
    override val isFragmentActivity: Boolean
        get() = false

    lateinit var showingUrl: String
    val presenter by lazy { DownloadPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_browse_pics)
        val photolist = intent.getStringArrayListExtra("photoes")
        val index = intent.getIntExtra("pos", -1)
        setFullScreen()
        //如果index为-1，展示地图，否则展示viewpager
        if (index == -1) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);//设置可以横屏
            iv_big_map.visible()
            showingUrl = photolist[0]
            iv_big_map.setImageFromUrl(showingUrl)
            tv_pic_index.gone()
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//只能竖屏
            vp_scenes.visible()
            tv_pic_index.text = "${index + 1}/${photolist.size}"
            showingUrl = photolist[index]
            val vpAdapter = SceneVpAdapter(photolist)
            vp_scenes.adapter = vpAdapter
            vp_scenes.setCurrentItem(index)
        }

        //更新照片下标
        vp_scenes.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                tv_pic_index.text = "${position + 1}/${photolist.size}"
                showingUrl = photolist[position]

            }
        })

    }

    override fun onStart() {
        super.onStart()
        iv_big_map.setOnLongClickListener(this)
        iv_download_pic.setOnClickListener {
            requestForAccess()
        }
    }

    //横屏时的监听器
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (newConfig?.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            iv_download_pic.visible()
        } else {
            iv_download_pic.gone()
        }
    }

    override fun onLongClick(v: View?): Boolean {
        //弹出下载窗口
        val contentView = LayoutInflater.from(BaseApp.context).inflate(R.layout.freshman_popwindow_download, null)
        reduceTransparency()
        val popupWindow =
            PopupWindow(contentView, (getScreenWidth() * 0.95).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
                .apply {
                    animationStyle = R.style.PopWindow
                    isFocusable = true
                    setOnDismissListener { resetTransparency() }
                    showAtLocation(fl_photo_content, Gravity.BOTTOM, 0, 50)
                }

        contentView.bt_download.setOnClickListener {
            requestForAccess()
            popupWindow.dismiss()
        }
        contentView.bt_down_cancle.setOnClickListener {
            popupWindow.dismiss()
        }
        return true
    }

    //申请权限并下载
    fun requestForAccess() {
        doPermissionAction(Manifest.permission.WRITE_EXTERNAL_STORAGE) {
            doAfterGranted {
                "${IMAGE}$showingUrl".let { it1 -> presenter.download(it1) }
            }
            doAfterRefused {
                toast("拒绝授权，该功能无法使用")
            }
        }
    }

    override fun toastSuccess() {
        runOnUiThread {
            toast("已下载")
        }
    }

    override fun toastFailed() {
        runOnUiThread {
            toast("下载失败！")
        }
    }

    private fun setFullScreen() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.ondestory()
    }


}


