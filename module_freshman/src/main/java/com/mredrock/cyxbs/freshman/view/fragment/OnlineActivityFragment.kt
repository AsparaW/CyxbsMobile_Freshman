package com.mredrock.cyxbs.freshman.view.fragment

import android.Manifest
import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.utils.extensions.doPermissionAction
import com.mredrock.cyxbs.common.utils.extensions.getScreenWidth
import com.mredrock.cyxbs.common.utils.extensions.setImageFromUrl
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.bean.ActivityBean
import com.mredrock.cyxbs.freshman.presenter.DownloadPresenter
import com.mredrock.cyxbs.freshman.presenter.OnlineActivityPresenter
import com.mredrock.cyxbs.freshman.reduceTransparency
import com.mredrock.cyxbs.freshman.resetTransparency
import com.mredrock.cyxbs.freshman.view.adapter.ActivityRecycleAdapter
import com.mredrock.cyxbs.freshman.view.iview.IDownloadView
import com.mredrock.cyxbs.freshman.view.iview.IOnlineView
import kotlinx.android.synthetic.main.freshman_dialog_qr_code.view.*
import kotlinx.android.synthetic.main.freshman_fragment_online_activity.*
import kotlinx.android.synthetic.main.freshman_popwindow_download.view.*
import org.jetbrains.anko.support.v4.runOnUiThread
import org.jetbrains.anko.support.v4.toast


/**
 * Created by tk on 2019/8/11
 */
class OnlineActivityFragment : Fragment(), IOnlineView, IDownloadView, ActivityRecycleAdapter.OnItemClickListener {

    lateinit var mAdapter: ActivityRecycleAdapter
    lateinit var dialog: AlertDialog
    val builder by lazy { AlertDialog.Builder(context) }
    val mDownloadPresenter by lazy { DownloadPresenter(this) }
    val mPresenter by lazy { OnlineActivityPresenter(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.freshman_fragment_online_activity, container, false)
        mPresenter.loadActivities()
        return view
    }


    override fun showActivities(list: List<ActivityBean.TextBean>) {
        mAdapter = ActivityRecycleAdapter(list, this)
        rv_activitys.adapter = mAdapter
        rv_activitys.layoutManager = LinearLayoutManager(context)
    }

    override fun toastSuccess() {
        runOnUiThread {
            toast("下载成功")
        }
    }

    override fun toastFailed() {
        runOnUiThread {
            toast("下载失败")
        }
    }

    override fun onItemClick(photo: String?, message: String?) {
        //弹出二维码dailog
        val view = LayoutInflater.from(context).inflate(R.layout.freshman_dialog_qr_code, null)
        view.iv_qr_code.setImageFromUrl(photo)
        view.tv_activity_message.text = message
        dialog = builder.setView(view).setCancelable(true).show()

        //长按二维码下载弹出下载框
        view.iv_qr_code.setOnLongClickListener {
            dialog.dismiss()
            activity?.reduceTransparency()
            val mView = LayoutInflater.from(context).inflate(R.layout.freshman_popwindow_download, null)
            val popupWindow =
                PopupWindow(mView, (activity?.getScreenWidth()!! * 0.95).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
            popupWindow.apply {
                isFocusable = true
                animationStyle = R.style.PopWindow
                setOnDismissListener { activity?.resetTransparency() }
                showAtLocation(fl_activity, Gravity.BOTTOM, 0, 50)
            }
            //下载
            mView.bt_download.setOnClickListener {
                //检查权限
                doPermissionAction(Manifest.permission.WRITE_EXTERNAL_STORAGE) {
                    doAfterGranted {
                        photo?.let { it -> mDownloadPresenter.download(it) }
                    }
                    doAfterRefused {
                        toast("拒绝授权，该功能无法使用")
                    }
                }
                popupWindow.dismiss()
            }
            resources
            //取消下载
            mView.bt_down_cancle.setOnClickListener {
                dialog.dismiss()
                popupWindow.dismiss()
            }
            true
        }

    }

}