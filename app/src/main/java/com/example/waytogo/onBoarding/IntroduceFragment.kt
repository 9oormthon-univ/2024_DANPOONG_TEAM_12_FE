package com.example.waytogo.onBoarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.example.waytogo.BaseFragment
import com.example.waytogo.CarPool.CarPoolActivity
import com.example.waytogo.databinding.FragmentIntroduceBinding

class IntroduceFragment : BaseFragment<FragmentIntroduceBinding>(FragmentIntroduceBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뒤로가기 버튼
        val navigationBtn : ImageButton = binding.navigationBtn
        navigationBtn.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }

        // 계속하기 버튼 누르면 카풀 페이지로 넘어가기
        binding.nextBtn.setOnClickListener {
            val intent = Intent(requireActivity(), CarPoolActivity::class.java)
            startActivity(intent)
        }
    }

}