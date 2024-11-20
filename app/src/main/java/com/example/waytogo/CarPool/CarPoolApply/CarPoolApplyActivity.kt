package com.example.waytogo.CarPool.CarPoolApply

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.waytogo.R
import com.example.waytogo.databinding.ActivityCarPoolApplyBinding
import com.example.waytogo.onBoarding.NickNameFragment

class CarPoolApplyActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCarPoolApplyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarPoolApplyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FindList Fragment로 바로 연결
        if(savedInstanceState == null){
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<FindListFragment>(R.id.carpool_fv)
            }
        }
    }

    // Fragment 변환
    fun changeFragment(fragment : Fragment){
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.carpool_fv, fragment)
            .commitAllowingStateLoss()
    }

    private fun setInit() {
        // MyNoticeFragment에서 넘어온 데이터
        val department = intent.getStringExtra("department")
        val destination = intent.getStringExtra("destination")

        // 값 넣어주기
        with(binding) {

        }
    }
}