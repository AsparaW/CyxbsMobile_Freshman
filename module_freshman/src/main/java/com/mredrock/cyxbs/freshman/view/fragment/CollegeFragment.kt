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
import com.mredrock.cyxbs.freshman.url.COLLEGE_URL
import com.mredrock.cyxbs.freshman.view.adapter.CommunicateAdapter
import com.mredrock.cyxbs.freshman.view.adapter.SearchRecycleAdapter
import com.mredrock.cyxbs.freshman.view.iview.ICommunicateView
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.freshman_fragment_college.*
import kotlinx.android.synthetic.main.freshman_popupwindow_search_result.view.*
import java.util.concurrent.TimeUnit


/**
 * Created by tk on 2019/8/10
 * 新生群fragment
 */
class CollegeFragment : Fragment(), ICommunicateView, SearchRecycleAdapter.OnSearchResultClickListener {

    lateinit var disposable: Disposable
    lateinit var mAdapter: CommunicateAdapter
    lateinit var popupWindow: PopupWindow
    val mPresenter by lazy { CommunicatePresenter(this) }
    lateinit var mList: List<CommunicateBean.TextBean>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.freshman_fragment_college, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mPresenter.loadContent(COLLEGE_URL)

        //监听输入框，获取联想数据
        disposable = Observable.create(ObservableOnSubscribe<String>() {
            et_college.addTextChangedListener(object : TextWatcher {

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
                mPresenter.getSearchCollege(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val view = LayoutInflater.from(context).inflate(R.layout.freshman_popupwindow_search_result, null)
                view.rv_serach_result.apply {
                    adapter = it.text.let { SearchRecycleAdapter(it, context, this@CollegeFragment) }
                    layoutManager = LinearLayoutManager(context)
                }
                popupWindow = PopupWindow(
                    view,
                    activity?.getScreenWidth()!! - activity?.dpToPx(30f)!!.toInt(),
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    isFocusable = true
                    showAsDropDown(et_college)
                }
            }
    }

    //展示学院群
    override fun showContent(list: List<CommunicateBean.TextBean>) {
        mList = list
        //添加分割线
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(BaseApp.context, R.drawable.freshman_recycle_divider)
            ?.let { divider.setDrawable(it) };
        rv_college.apply {
            addItemDecoration(divider);
            adapter = context?.let { CommunicateAdapter(list, it) }!!
            layoutManager = LinearLayoutManager(context)

        }
    }


    //搜索框联想数据点击后指定item置顶
    override fun onSearchItemClick(name: String) {
        popupWindow.dismiss()
        var index: Int = 0
        for (i in 0 until mList.size) {
            if (mList[i].name.equals(name)) {
                index = i
                break
            }
        }
        (rv_college.getLayoutManager() as LinearLayoutManager).scrollToPositionWithOffset(index, 0)

    }


    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }


}
