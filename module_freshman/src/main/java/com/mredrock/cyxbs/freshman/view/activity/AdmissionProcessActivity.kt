package com.mredrock.cyxbs.freshman.view.activity

import android.app.ProgressDialog
import android.os.Bundle
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.freshman.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.freshman.bean.AdminProBean
import com.mredrock.cyxbs.freshman.presenter.AdminProPresenter
import com.mredrock.cyxbs.freshman.view.adapter.AdminProRecycleAdapter
import com.mredrock.cyxbs.freshman.view.iview.IAdminProView
import kotlinx.android.synthetic.main.freshman_activity_admission_process.*


class AdmissionProcessActivity: BaseActivity(),IAdminProView {

    lateinit var progressDialog:ProgressDialog
    lateinit var adapter: AdminProRecycleAdapter
    lateinit var presenter: AdminProPresenter
    override val isFragmentActivity: Boolean
        get() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.freshman_activity_admission_process)
        common_toolbar.init("入学流程")
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle(null)
        progressDialog.setCancelable(true)
        presenter = AdminProPresenter(this)
        presenter.loadProcess()


    }

    override fun showDialog() {
        if (!progressDialog.isShowing){
            progressDialog.show()
        }
    }

    override fun DismissDialog() {
        if (progressDialog.isShowing){
            progressDialog.dismiss()
        }
    }

    override fun showProcess(adminProBean: AdminProBean) {
        adapter = AdminProRecycleAdapter(adminProBean)
        val layoutmanger = LinearLayoutManager(this)
        rv_admission_pro.adapter = adapter
        rv_admission_pro.layoutManager = layoutmanger
    }




}
