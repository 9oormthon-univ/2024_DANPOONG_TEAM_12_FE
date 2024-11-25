package com.example.waytogo.CarPool

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.waytogo.R
import com.example.waytogo.databinding.ActivityCarPoolBinding
import com.example.waytogo.onBoarding.NickNameFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarPoolActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarPoolBinding

    private var shouldShowDialog: Boolean = false


    private val tabTextList = listOf("카풀 찾기", "지원 내역")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarPoolBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()
        binding.carpoolVp.run {
            isUserInputEnabled = false
        }
//
//        // CarPool Fragment로 바로 연결
//        if(savedInstanceState == null){
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add<FindCarPoolFragment>(R.id.carpool_fragment)
//            }
//        }

        // Intent 데이터 저장
        shouldShowDialog = intent.getBooleanExtra("showDialog", false)
        // FAB 버튼 누르면 글 쓰기 프래그먼트로 이동
        binding.carpoolIb.setOnClickListener {
            val intent = Intent(this, CarPoolPostUpActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (shouldShowDialog) {
            showDialog()
            shouldShowDialog = false // 다이얼로그가 반복적으로 표시되지 않도록 설정
        }
    }

    // 글 올기기 버튼 누르면 dialog 띄우기
    private fun showDialog() {
        val v1 = layoutInflater.inflate(R.layout.custom_dialog, null)
        val builder = AlertDialog.Builder(this)
            .setView(v1)
            .show()
//        CustomDialog(this, resources.getString(R.string.post_up_alret)).show()
    }

    // Fragment 변환
    fun changeFragment(fragment : Fragment){
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.carpool_vp, fragment)
            .commitAllowingStateLoss()
    }

    private fun initViewPager(){
        val viewPager = binding.carpoolVp
        val viewPagerAdapter = CarPoolViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        // TabLayout과 연결
        TabLayoutMediator(binding.carpoolTablayout, viewPager){ tab, position ->
            tab.text = tabTextList[position]
        }.attach()
    }

}