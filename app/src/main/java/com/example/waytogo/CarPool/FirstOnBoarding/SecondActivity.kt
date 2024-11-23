package com.example.waytogo.CarPool.FirstOnBoarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.waytogo.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        val backbtn : ImageView = findViewById(R.id.onboarding2_backbtn)
        val nextbtn : Button = findViewById(R.id.onboarding2_nextbtn)

        backbtn.setOnClickListener {
            finish()
        }
        nextbtn.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivity(intent)
        }
    }
}