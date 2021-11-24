package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class TabPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_pager)

        // --> 탭 생성
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.addTab(tabLayout.newTab().setText("ONE"))
        tabLayout.addTab(tabLayout.newTab().setText("TWO"))
        tabLayout.addTab(tabLayout.newTab().setText("THREE"))

        val pagerAdapter = FragmentPagerAdapter(supportFragmentManager, 3)
        val viewPager = findViewById<ViewPager>(R.id.view_pager)
        viewPager.adapter = pagerAdapter

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        // -> 페이저가 이동했을 때 탭을 이동시키는 코드
    }
}

class FragmentPagerAdapter(fragmentManager: FragmentManager, val tabCount: Int):FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                Fragment1()
            }
            1 -> {
                Fragment2()
            }
            2 -> {
                Fragment3()
            }
            else -> Fragment1()
        }
    }
}