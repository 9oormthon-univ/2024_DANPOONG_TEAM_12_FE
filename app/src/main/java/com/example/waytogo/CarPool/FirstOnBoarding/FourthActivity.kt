package com.example.waytogo.CarPool.FirstOnBoarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.waytogo.HomeActivity
import com.example.waytogo.R

class FourthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fourth)
        val backbtn : ImageView = findViewById(R.id.onboarding4_backbtn)
        val nextbtn : Button = findViewById(R.id.onboarding4_nextbtn)

        backbtn.setOnClickListener {
            finish()
        }
        nextbtn.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            intent.putExtra("onBoarding", 1234)
            startActivity(intent)

        }
    }
}