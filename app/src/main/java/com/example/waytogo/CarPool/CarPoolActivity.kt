package com.example.waytogo.CarPool

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.waytogo.R
import com.example.waytogo.databinding.ActivityCarPoolBinding
import com.example.waytogo.onBoarding.NickNameFragment
import com.google.android.material.tabs.TabLayoutMediator

class CarPoolActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarPoolBinding

    private val tabTextList = listOf("카풀 찾기", "지원 내역")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarPoolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()
//
//        // CarPool Fragment로 바로 연결
//        if(savedInstanceState == null){
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add<FindCarPoolFragment>(R.id.carpool_fragment)
//            }
//        }
    }

    // Fragment 변환
    fun changeFragment(fragment : Fragment){
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.carpool_vp, fragment)
            .commitAllowingStateLoss()
    }

    fun initViewPager(){
        val viewPager = binding.carpoolVp
        val viewPagerAdapter = CarPoolViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        // TabLayout과 연결
        TabLayoutMediator(binding.carpoolTablayout, viewPager){ tab, position ->
            tab.text = tabTextList[position]
        }.attach()
    }
}