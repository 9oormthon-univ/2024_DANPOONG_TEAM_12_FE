package com.example.waytogo.onBoarding

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.waytogo.BaseFragment
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentNickNameBinding
import com.example.waytogo.utils.Constants.TAG
import com.example.waytogo.utils.onMyTextChanged

class NickNameFragment : BaseFragment<FragmentNickNameBinding>(FragmentNickNameBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nickname = binding.nicknameEt
        val nextButton = binding.nextBtn

        // 닉네임 변경될 경우 버튼 활성화
        nickname.onMyTextChanged {
            if (it.toString().count() > 0) {
                binding.checkBtn.apply {
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                    isEnabled = true
                }
            }
            else {
                binding.checkBtn.apply {
                    isEnabled = false
                }
            }
            if (it.toString().count() == 10){
                Toast.makeText(requireContext(), "최대 10자까지 입력간응합니당!", Toast.LENGTH_SHORT).show()
            }
        }

        // 중복 체크
        binding.checkBtn.setOnClickListener{
            binding.doubleCheckTv.visibility = View.VISIBLE
            activity?.let { it1 -> WindowInsetsControllerCompat(it1.window, it1.window.decorView).hide(WindowInsetsCompat.Type.ime()) }
            if (nickname.text.toString().count() >= 1){
                binding.nicknameEt.apply {
                    setBackgroundResource(R.drawable.edittext_input_background)
                    binding.doubleCheckTv.apply{
                        text = "사용가능한 닉네임 입니다"
                        setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.blue)))
                    }
                }
                nextButton.apply {
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                    isEnabled = true
                }
            }
            else{
                binding.nicknameEt.apply {
                    binding.doubleCheckTv.apply{
                        text = "이미 사용중인 닉네임 입니다"
                        setTextColor(ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray300)))
                    }
                }
                nextButton.apply {
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
                    isEnabled = false
                }
            }

        }

        binding.nextBtn.setOnClickListener {
            (activity as OnBoardingActivity).changeFragment(InterestedFragment())
        }

    }
}