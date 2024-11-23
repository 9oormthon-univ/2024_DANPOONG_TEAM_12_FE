package com.example.waytogo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.example.waytogo.Course.CourseFragment
import com.example.waytogo.databinding.ActivityHomeBinding
import com.example.waytogo.information.InfoFragment

class HomeActivity : AppCompatActivity() {
    private val binding : ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 상태바와 콘텐츠 충돌 방지
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(binding.root)

        // 상태바 색상 설정
        window.statusBarColor = resources.getColor(R.color.blue, theme)

        setBottomNavigationView()

        if(savedInstanceState == null) {
            binding.homenavigationBnv.selectedItemId = R.id.nav_home
        }

        // Intent로 전달된 데이터 확인
        val fragmentId = intent.getIntExtra("id", R.id.nav_home)
        val bottomNav = binding.homenavigationBnv
        bottomNav.selectedItemId = fragmentId

        // 전달된 ID에 따라 프래그먼트 표시
        when (fragmentId) {
            R.id.nav_home -> {
                showFragment(HomeFragment())
            }
            R.id.nav_info -> {
                showFragment(InfoFragment())
            }
            R.id.nav_course -> {
                showFragment(CourseFragment())
            }
            R.id.nav_mypage -> {
                showFragment(MypageFragment())
            }
        }

        val onBoarding = intent.getIntExtra("onBoarding", 0)
        if (onBoarding == 1234){
            showFragment(HomeFragment())
        }

    }

    // 프래그먼트 이동
    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentcontainer_fl, fragment)
            .commit()
    }

    fun setBottomNavigationView() {
        binding.homenavigationBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentcontainer_fl,HomeFragment()).commit()
                    true
                }
                R.id.nav_info -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentcontainer_fl,
                        InfoFragment()
                    ).commit()
                    true
                }
                R.id.nav_course -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentcontainer_fl,
                        CourseFragment()
                    ).commit()
                    true
                }
                R.id.nav_mypage -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentcontainer_fl,MypageFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }
}
