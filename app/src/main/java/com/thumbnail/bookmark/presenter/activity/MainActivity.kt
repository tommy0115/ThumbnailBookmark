package com.thumbnail.bookmark.presenter.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.thumbnail.bookmark.R
import com.thumbnail.bookmark.presenter.util.FragmentStateAdapterExt

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentStateAdapterExt: FragmentStateAdapterExt
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.pager)
        fragmentStateAdapterExt = FragmentStateAdapterExt(supportFragmentManager, lifecycle)
        viewPager.adapter = fragmentStateAdapterExt

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = FragmentStateAdapterExt.getTabTitle(this, position)
        }.attach()
    }
}
