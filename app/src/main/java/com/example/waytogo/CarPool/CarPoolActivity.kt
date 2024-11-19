package com.example.waytogo.CarPool

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.waytogo.R
import com.example.waytogo.onBoarding.NickNameFragment

class CarPoolActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_pool)

        // CarPool Fragment로 바로 연결
        if(savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<NickNameFragment>(R.id.onboarding_fragment)
            }
        }
    }

    // Fragment 변환
    fun changeFragment(fragment : Fragment){
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.onboarding_fragment, fragment)
            .commit()
    }
}