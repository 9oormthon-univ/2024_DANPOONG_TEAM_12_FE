package com.example.waytogo.CarPool

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.waytogo.CarPool.CarPoolApplication.CarPoolApplicationFragment

class CarPoolViewPagerAdapter(fragmentActivity: CarPoolActivity): FragmentStateAdapter(fragmentActivity) {
    private lateinit var viewPagerAdapter: CarPoolViewPagerAdapter
    val fragments = listOf<Fragment>(FindCarPoolFragment(), CarPoolApplicationFragment())
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}