package com.example.waytogo.CarPool.CarPoolApply

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.waytogo.HomeActivity
import com.example.waytogo.R
import com.example.waytogo.databinding.ActivityCarPoolApplyBinding
import com.example.waytogo.onBoarding.NickNameFragment
import com.example.waytogo.utils.Constants.TAG

class CarPoolApplyActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCarPoolApplyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarPoolApplyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FindList Fragment로 바로 연결
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.carpool_fv, passingLocation(FindListFragment()))
        }

        setBottomNavigationView()
    }

    // Fragment 변환
    fun changeFragment(fragment : Fragment){
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.carpool_fv, fragment)
            .commitAllowingStateLoss()
    }

    // 검색한 위치 정보 넘겨주기
    private fun passingLocation(fragment: Fragment): Fragment {
        // MyNoticeFragment에서 넘어온 데이터
        val startLocation = intent.getStringExtra("startLocation")
        val endLocation = intent.getStringExtra("endLocation")

        // ProfileFragment에 다시 데이터 전달
        val bundle = Bundle()
        bundle.putString("startLocation", startLocation)
        bundle.putString("endLocation", endLocation)
        fragment.arguments = bundle

        return fragment
    }

    private fun setBottomNavigationView() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            Log.d(TAG, "${item.itemId}\n${R.id.nav_home}")
            when (item.itemId) {
                R.id.nav_home -> {
                    // HomeActivity로 이동하며 ID 전달
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("id", R.id.nav_home) // HomeFragment를 표시
                    startActivity(intent)
                    true
                }
                R.id.nav_info -> {
                    Log.d(TAG, "응 눌리고 있어")
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("id", R.id.nav_info) // InfoFragment를 표시
                    startActivity(intent)
                    true
                }
                R.id.nav_course -> {
                    Log.d(TAG, "응 눌리고 있어")
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("id", R.id.nav_course) // CourseFragment를 표시
                    startActivity(intent)
                    true
                }
                R.id.nav_mypage -> {
                    Log.d(TAG, "응 눌리고 있어")
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("id", R.id.nav_mypage) // MypageFragment를 표시
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}