package com.example.waytogo.onBoarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import com.example.waytogo.R
import androidx.fragment.app.commit

class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        // NickName Fragment로 바로 연결
        if(savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<NickNameFragment>(R.id.onboarding_fragment)
            }
        }
    }

    fun changeFragment(fragment : Fragment){
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.onboarding_fragment, fragment)
            .commit()
    }
}