package com.mredrock.cyxbs.freshman.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mredrock.cyxbs.common.network.ApiGenerator
import com.mredrock.cyxbs.common.ui.BaseActivity
import com.mredrock.cyxbs.freshman.BuildConfig

import com.mredrock.cyxbs.freshman.R
//import com.mredrock.cyxbs.freshman.service.SearchService
import com.mredrock.cyxbs.freshman.view.adapter.CommunicateAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.freshman_recycle_item_content.view.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : BaseActivity() {
    override val isFragmentActivity: Boolean
        get() = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val logInterceptor = HttpLoggingInterceptor()
//        if (BuildConfig.DEBUG) {
//            //显示日志
//            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        } else {
//            logInterceptor.level = HttpLoggingInterceptor.Level.NONE
//        }
//        val okHttpClient =   OkHttpClient.Builder()
//            .connectTimeout(80000, TimeUnit.SECONDS)//设置超时时间
//            .retryOnConnectionFailure(true)
//        okHttpClient.addInterceptor(logInterceptor)

//        val mService = ApiGenerator.getApiService(SearchService::class.java)
//        val disposable = mService.getCollegeNum("http://129.28.185.138:9025/zsqy/select/college","计算机")
//            .setSchedulers()
//            .subscribe({
//                Log.d("testcollege",it.info)
//            },{
//                Log.d("excption", it.message)
//            })





    }
    fun show(){
        val dialog2 =  AlertDialog.Builder(this);
        dialog2.setTitle("提示").setMessage("确认删除？").setPositiveButton("确定", null).setNegativeButton("取消", null).show();

    }
}
