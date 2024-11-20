package com.example.waytogo.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waytogo.BaseFragment
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentInterestedBinding
import com.example.waytogo.onBoarding.Interest.InterestAdapter
import com.example.waytogo.onBoarding.Interest.InterestData

class InterestedFragment : BaseFragment<FragmentInterestedBinding>(FragmentInterestedBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nextButton = binding.nextBtn

        // 리사이클러뷰 그리드, 수직 정렬
        binding.interestRv.layoutManager = GridLayoutManager(requireContext(), 3)
        initRecycler()

        // 뒤로가기 버튼
        val navigationBtn : ImageButton = binding.navigationBtn
        navigationBtn.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }

        // 다음 버튼
        binding.nextBtn.setOnClickListener{
            (activity as OnBoardingActivity).changeFragment(IntroduceFragment())
        }
    }

    private fun initRecycler(){
        val itemList = mutableListOf<InterestData>(
            InterestData(R.drawable.alone_icon, "혼자서", false),
            InterestData(R.drawable.with_friends_logo, "친구와 함께", false),
            InterestData(R.drawable.food_logo, "맛집 투어", false),
            InterestData(R.drawable.memo_logo, "J 여행", false),
            InterestData(R.drawable.spontaneous_logo, "P 여행", false),
            InterestData(R.drawable.dog_logo, "애견 동반", false),
            InterestData(R.drawable.healing_logo, "힐링", false),
            InterestData(R.drawable.experiment_logo, "체험", false),
            InterestData(R.drawable.tour_loog, "관광", false),
            InterestData(R.drawable.activity_logo, "액티비티", false),
            InterestData(R.drawable.photo_logo, "포토명소", false),
            InterestData(R.drawable.walk_logo, "뚜벅이", false),
            InterestData(R.drawable.public_transportation_logo, "대중교통", false),
            InterestData(R.drawable.taxi_logo, "택시", false),
            InterestData(R.drawable.drive_logo, "직접 운전", false),

        )

        val adapter = InterestAdapter(itemList)
        binding.interestRv.apply {
            setAdapter(adapter)
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        }
        adapter.setItemClickListener(object : InterestAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

            }
        })
    }

}