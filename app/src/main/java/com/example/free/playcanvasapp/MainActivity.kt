package com.example.free.playcanvasapp

import android.support.v7.app.AppCompatActivity
import android.support.design.widget.TabLayout
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager

class MainActivity : AppCompatActivity() {
    private var tabLayout: TabLayout? = null
    private var pager: ViewPager? = null
    var pageModels: MutableList<PageModel> = emptyList<PageModel>().toMutableList()

    init {
        pageModels.add(PageModel(R.string.clip_rect, R.layout.view_clip_rect))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pager = findViewById(R.id.pager)
        pager?.adapter = object : FragmentPagerAdapter(supportFragmentManager) {

            override fun getItem(position: Int): Fragment {
                val pageModel = pageModels[position]
                return PageFragment.newInstance(pageModel.titleRes, pageModel.layoutRes)
            }

            override fun getCount(): Int {
                return pageModels.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return getString(pageModels[position].titleRes)
            }
        }

        tabLayout = findViewById(R.id.tabLayout)
        tabLayout?.setupWithViewPager(pager)
    }

    data class PageModel constructor(
            @param:StringRes @field:StringRes internal var titleRes: Int,
            @param:LayoutRes @field:LayoutRes internal var layoutRes: Int)
}
