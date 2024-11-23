package com.example.waytogo.CarPool.CarPoolApply

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waytogo.BaseFragment
import com.example.waytogo.CarPool.CarPoolList.CarPoolListAdapter
import com.example.waytogo.CarPool.CarPoolList.ListData
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentFindListBinding
import com.example.waytogo.onBoarding.Interest.InterestAdapter
import com.example.waytogo.utils.Constants.TAG
import com.google.gson.Gson

class FindListFragment : BaseFragment<FragmentFindListBinding>(FragmentFindListBinding::inflate) {

    val itemList = mutableListOf<ListData>(
        ListData(R.drawable.dummy_image_1, "배봉까지 같이 가요!", "출발지: 대한민국 역사 박물관", "목적지: 서울 동대문구 휘경2동", "날짜: 2024. 11. 23", "시간: 오전 9시"),
        ListData(R.drawable.dummy_image_2, "바다보러 해운대 갑시다!", "출발지: 서울 종로구", "목적지: 해운대역", "날짜: 2024. 11. 22", "시간: 오전 7시"),
        ListData(R.drawable.dummy_image_3, "춘천 같이 가실 분", "출발지: 서울 종로구", "목적지: 춘천", "날짜: 2024. 11. 24", "시간: 오후 3시"),
//        ListData(R.drawable.ic_launcher_foreground, "배봉까지 같이 가요!", "출발지: 대한민국 역사 박물관", "도착지: 서울 동대문구 휘경2동", "날짜: 2024. 11. 23", "시간: 오전 9시"),
//        ListData(R.drawable.ic_launcher_foreground, "배봉까지 같이 가요!", "출발지: 대한민국 역사 박물관", "도착지: 서울 동대문구 휘경2동", "날짜: 2024. 11. 23", "시간: 오전 9시"),
//        ListData(R.drawable.ic_launcher_foreground, "배봉까지 같이 가요!", "출발지: 대한민국 역사 박물관", "도착지: 서울 동대문구 휘경2동", "날짜: 2024. 11. 23", "시간: 오전 9시"),
//        ListData(R.drawable.ic_launcher_foreground, "배봉까지 같이 가요!", "출발지: 대한민국 역사 박물관", "도착지: 서울 동대문구 휘경2동", "날짜: 2024. 11. 23", "시간: 오전 9시"),
//        ListData(R.drawable.ic_launcher_foreground, "배봉까지 같이 가요!", "출발지: 대한민국 역사 박물관", "도착지: 서울 동대문구 휘경2동", "날짜: 2024. 11. 23", "시간: 오전 9시")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 출발점, 도착점 데이터 가져오기
        setInit()
        // recyclerView adapter설정
        setList()

    }

    private fun setInit() {

        val gson = Gson()
        // argument에서 데이터를 꺼내기
        val startLocation = arguments?.getString("startLocation")
        val endLocation = arguments?.getString("endLocation")

        with(binding){
            startLocationTv.text = startLocation
            endLocationTv.text = endLocation
        }
    }

    // 리사이클러 뷰 어댑터 설정
    private fun setList(){
        val adapter = CarPoolListAdapter(itemList)
        binding.applicationListRv.apply {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        }
        adapter.setItemClickListener(object : CarPoolListAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                moveToPostFragment(itemList[position])
            }
        })
    }

    // Fragment 이동하면서 데이터 옮기기
    private fun moveToPostFragment(itemList: ListData) {
        Log.d("HomeFragment", "ListData: $itemList")
        (context as CarPoolApplyActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.carpool_fv, CarPoolPostFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val CarpoolInformJson = gson.toJson(itemList)
                    putString("CarpoolInform", CarpoolInformJson) // data class 데이터
                }
            })
            .addToBackStack(null) // 백 스택에 트랜잭션을 추가
            .commitAllowingStateLoss()
    }

}