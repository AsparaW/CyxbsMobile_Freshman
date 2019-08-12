package com.mredrock.cyxbs.freshman.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Created by tk on 2019/8/9
 *
 */
class PagerAdapter(val title: Array<String>, val fm: FragmentManager, val listFragment: ArrayList<Fragment>) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment = listFragment[position]

    override fun getCount(): Int = listFragment.size

    override fun getPageTitle(position: Int): CharSequence {
        //给viewpager的每个界面设置title
        return title[position]
    }

}