package com.mredrock.cyxbs.freshman.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mredrock.cyxbs.common.BaseApp
import com.mredrock.cyxbs.common.utils.extensions.getScreenWidth
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.bean.CommunicateBean
import com.mredrock.cyxbs.freshman.dpToPx
import com.mredrock.cyxbs.freshman.presenter.CommunicatePresenter
import com.mredrock.cyxbs.freshman.url.HOME_TOWN
import com.mredrock.cyxbs.freshman.view.adapter.CommunicateAdapter
import com.mredrock.cyxbs.freshman.view.adapter.SearchRecycleAdapter
import com.mredrock.cyxbs.freshman.view.iview.ICommunicateView
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.freshman_fragment_province.*
import kotlinx.android.synthetic.main.freshman_popupwindow_search_result.view.*
import java.util.concurrent.TimeUnit

/**
 * Created by tk on 2019/8/11
 */
class ProvinceFragment : Fragment(), ICommunicateView, SearchRecycleAdapter.OnSearchResultClickListener {


    val mPresenter by lazy { CommunicatePresenter(this) }
    lateinit var disposable: Disposable
    lateinit var mList: List<CommunicateBean.TextBean>
    lateinit var popupWindow: PopupWindow
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.freshman_fragment_province, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mPresenter.loadContent(HOME_TOWN)
        disposable = Observable.create(ObservableOnSubscribe<String>() {
            et_province.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable?) = it.onNext(s.toString())

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            })
        }).debounce(500, TimeUnit.MILLISECONDS)
            .filter {
                if (!TextUtils.isEmpty(it)) true
                else false
            }
            .flatMap {
                mPresenter.getSearchProvince(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val view = LayoutInflater.from(context).inflate(R.layout.freshman_popupwindow_search_result, null)
                view.rv_serach_result.apply {
                    adapter = it.text.let { SearchRecycleAdapter(it, context, this@ProvinceFragment) }
                    layoutManager = LinearLayoutManager(context)
                }
                popupWindow = PopupWindow(
                    view,
                    activity?.getScreenWidth()!! - activity?.dpToPx(30f)!!.toInt(),
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    isFocusable = true
                    showAsDropDown(et_province)

                }
            }
    }


    //展示老乡群数据
    override fun showContent(list: List<CommunicateBean.TextBean>) {
        mList = list
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(BaseApp.context, R.drawable.freshman_recycle_divider)
            ?.let { divider.setDrawable(it) };
        rv_hometown.apply {
            addItemDecoration(divider);
            adapter = context?.let { CommunicateAdapter(list, it) }!!
            layoutManager = LinearLayoutManager(context)

        }
    }

    //点击老乡群搜索联想item，置顶相应老乡群
    override fun onSearchItemClick(name: String) {
        popupWindow.dismiss()
        et_province.setText("")
        var index: Int = 0
        for (i in 0 until mList.size) {
            if (mList[i].name.equals(name)) {
                index = i
                break
            }
        }
        (rv_hometown.getLayoutManager() as LinearLayoutManager).scrollToPositionWithOffset(index, 0)
    }
}