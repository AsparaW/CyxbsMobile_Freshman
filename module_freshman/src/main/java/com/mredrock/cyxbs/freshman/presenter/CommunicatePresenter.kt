package com.mredrock.cyxbs.freshman.presenter

import com.mredrock.cyxbs.common.utils.LogUtils
import com.mredrock.cyxbs.freshman.bean.CommunicateBean
import com.mredrock.cyxbs.freshman.model.CommunicateModel
import com.mredrock.cyxbs.freshman.model.SeacrchModel
import com.mredrock.cyxbs.freshman.url.COLLEGE_URL
import com.mredrock.cyxbs.freshman.url.SEARCH_COLLEGE
import com.mredrock.cyxbs.freshman.view.iview.ICommunicateView
import io.reactivex.Observable

/**
 * Created by tk on 2019/8/11
 */
class CommunicatePresenter(var mView: ICommunicateView?) : BasePresenter{



    val mListener =  object : OnloadModelListener{
        override fun <T> success(t: T) {
            val mBean = t as CommunicateBean
            mBean.text?.let { mView?.showContent(it) }
        }

        override fun failed() {
            LogUtils.d("onCommunicatePresenter","listener failed")
        }
    }
    var mModel = SeacrchModel()
    var model = CommunicateModel(mListener)

    fun getSearchCollege(value: String) : Observable<CommunicateBean> =
         mModel.getCollegeObservable(value)
    fun getSearchProvince(value: String) : Observable<CommunicateBean> =
         mModel.getProvinceObservable(value)

    fun loadContent(url :String){
        model.getResult(url)
    }
    override fun ondestory() {
        mView = null
        mModel.destory()
    }
}