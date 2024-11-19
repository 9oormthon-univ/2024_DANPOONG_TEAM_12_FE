package com.example.waytogo.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatButton
import com.example.waytogo.BaseFragment
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentInterestedBinding

// Fragment Index 1
class InterestedFragment : BaseFragment<FragmentInterestedBinding>(FragmentInterestedBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nextButton = binding.nextBtn

        // 다음 버튼
//        nextButton.setOnClickListener{
//            (activity as OnBoardingActivity).changeFragment()
//        }

        // 뒤로가기 버튼
        val navigationBtn : ImageButton = binding.navigationBtn
        navigationBtn.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

}