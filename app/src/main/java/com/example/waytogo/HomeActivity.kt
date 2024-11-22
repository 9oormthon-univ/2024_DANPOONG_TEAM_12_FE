package com.example.waytogo

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.waytogo.Course.CourseFragment
import com.example.waytogo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private val binding : ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 상태바와 콘텐츠 충돌 방지
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(binding.root)

        val topBar = findViewById<View>(R.id.homeTopBar_fl)

        // WindowInsets를 사용해 상태바 높이를 강제로 적용
        ViewCompat.setOnApplyWindowInsetsListener(topBar) { view, insets ->
            val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            Log.d("Insets", "StatusBar height: $statusBarHeight")

            // FrameLayout에 패딩 적용
            view.setPadding(0, statusBarHeight, 0, 0)
            insets
        }

        // 상태바 색상 설정
        window.statusBarColor = resources.getColor(R.color.blue, theme)

        setBottomNavigationView()

        if(savedInstanceState == null) {
            binding.homenavigationBnv.selectedItemId = R.id.nav_home
        }
    }

    fun setBottomNavigationView() {
        binding.homenavigationBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentcontainer_fl,HomeFragment()).commit()
                    true
                }
                R.id.nav_info -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentcontainer_fl,InfoFragment()).commit()
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
