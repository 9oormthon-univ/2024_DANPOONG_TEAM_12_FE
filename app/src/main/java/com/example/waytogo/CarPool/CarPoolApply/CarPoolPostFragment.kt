package com.example.waytogo.CarPool.CarPoolApply

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waytogo.BaseFragment
import com.example.waytogo.CarPool.CarPoolList.ListData
import com.example.waytogo.CarPool.CarPoolModal.CarpoolApplyModal
import com.example.waytogo.CarPool.CarPoolModal.CarpoolModalFragment
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentCarPoolPostBinding
import com.google.gson.Gson

class CarPoolPostFragment : BaseFragment<FragmentCarPoolPostBinding>(FragmentCarPoolPostBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // FindListFragment에서 넘어온 데이터 넣어주기
        receiveHomeData()

        // 지원하기 버튼을 누를 경우 Modal 창 띄우기
        binding.applyBtn.setOnClickListener {
            applyModal()
        }
    }

    private var gson: Gson = Gson()

    // 게시글 정보 데이터 수신
    private fun receiveHomeData() {
        /* argument에서 데이터를 꺼내기*/
        // data class 데이터
        val CarpoolInformJson = arguments?.getString("CarpoolInform")
        val postInform = gson.fromJson(CarpoolInformJson, ListData::class.java)
        // 뷰 랜더링
        setInit(postInform)
    }

    // binding해서 각 정보 대입해주기
    private fun setInit(itemList: ListData) {
        // FindListFragment 에서 넘어온 게시글 정보 데이터
        val title = itemList.title
        val destination = itemList.destination
        val date = itemList.date
        val time = itemList.time

        // 값 넣어주기
        with(binding) {
            carpoolPostTitleTv.text = title
            carpoolPostDestinationTv.text = destination
            carpoolPostDateTv.text = date
            carpoolPostTimeTv.text = time
        }
    }

    // Modal 창 띄우기
    private fun applyModal(){
        val carpoolModal = CarpoolApplyModal()
        carpoolModal.show(parentFragmentManager, CarpoolApplyModal.TAG)
    }
}