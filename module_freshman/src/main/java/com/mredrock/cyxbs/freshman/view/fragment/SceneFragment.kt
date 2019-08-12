package com.mredrock.cyxbs.freshman.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mredrock.cyxbs.common.component.PhotoViewerActivity
import com.mredrock.cyxbs.common.utils.extensions.setImageFromUrl
import com.mredrock.cyxbs.freshman.R
import com.mredrock.cyxbs.freshman.bean.SceneBean
import com.mredrock.cyxbs.freshman.presenter.ScenePresenter
import com.mredrock.cyxbs.freshman.url.IMAGE
import com.mredrock.cyxbs.freshman.view.activity.BrowsePicsActivity
import com.mredrock.cyxbs.freshman.view.adapter.SceneRecycleAdapter
import com.mredrock.cyxbs.freshman.view.iview.ISceneView
import kotlinx.android.synthetic.main.freshman_fragment_scene.*
import org.jetbrains.anko.startActivity

/**
 * Created by tk on 2019/8/9
 * 校园风光
 */
class SceneFragment : Fragment() ,ISceneView {

    private lateinit var sRecycleAdapter: SceneRecycleAdapter
    val presenter by lazy { ScenePresenter(this) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.freshman_fragment_scene, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.loadScene()

    }

    override fun showMap(title: String?, photo: String?) {
        tv_map_name.text = title
        iv_cqupt_map_2d.setImageFromUrl("${IMAGE}${photo}")

        iv_cqupt_map_2d.setOnClickListener {
            //显示大图
            val list = photo?.let { listOf<String>(it) }
            context?.startActivity<BrowsePicsActivity>("photoes" to list)
        }

    }


    override fun showScenes(list: List<SceneBean.TextBean.MessageBean>) {

        sRecycleAdapter = SceneRecycleAdapter(list,activity)
        rv_school_photos.adapter = sRecycleAdapter
        val manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_school_photos.layoutManager = manager

    }

}