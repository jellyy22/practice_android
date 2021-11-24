package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.myapplication2.R
import com.google.android.material.tabs.TabLayout

class TabPager2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_pager2)

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout2)
        tabLayout.addTab(tabLayout.newTab().setText("ONE"))
        tabLayout.addTab(tabLayout.newTab().setText("TWO"))
        tabLayout.addTab(tabLayout.newTab().setText("THREE"))

        val adapter = ThreePageAdapter(LayoutInflater.from(this@TabPager2Activity))
        val viewPager = findViewById<ViewPager>(R.id.view_pager2)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        // -> 페이저가 이동했을 때 탭을 이동시키는 코드

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }
}

class ThreePageAdapter(val layoutInflater: LayoutInflater) : PagerAdapter() {
    override fun getCount(): Int {
        return 3
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
        // -> 보고있는 화면과 리턴시킨 화면이 맞는지 비교하는 부분
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        when (position) {
            0 -> {
                val view = layoutInflater.inflate(R.layout.fragment_one, container, false)
                container.addView(view)
                return view
            }
            1 -> {
                val view = layoutInflater.inflate(R.layout.fragment_two, container, false)
                container.addView(view)
                return view
            }

            2 -> {
                val view = layoutInflater.inflate(R.layout.fragment_three, container, false)
                container.addView(view)
                return view
            }

            else -> {
                val view = layoutInflater.inflate(R.layout.fragment_one, container, false)
                container.addView(view)
                return view
            }
        }
    }
}