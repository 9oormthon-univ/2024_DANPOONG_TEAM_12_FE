package com.example.waytogo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 상태바와 콘텐츠 충돌 방지
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(R.layout.activity_home)

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
    }
}
