package com.example.waytogo.CarPool.CarPoolApplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.waytogo.BaseFragment
import com.example.waytogo.CarPool.CarPoolApplication.CarPoolApplicationList.ApplyList
import com.example.waytogo.CarPool.CarPoolApplication.CarPoolApplicationList.CarPoolApplyAdapter
import com.example.waytogo.CarPool.CarPoolModal.CarpoolApplicationModal
import com.example.waytogo.CarPool.CarPoolModal.CarpoolCancelModal
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentCarPoolApplicationBinding
import com.example.waytogo.utils.Constants.TAG
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CarPoolApplicationFragment : BaseFragment<FragmentCarPoolApplicationBinding>(FragmentCarPoolApplicationBinding::inflate) {

    val itemList = mutableListOf(
        ApplyList(R.drawable.dummy_image_1, "배봉까지 같이 가요!", "출발지: 대한민국 역사 박물관", "목적지: 서울 동대문구 휘경2동", "날짜: 2024. 11. 23", "시간: 오전 9시"),
        ApplyList(R.drawable.dummy_image_2, "바다보러 해운대 갑시다!", "출발지: 서울 종로구", "목적지: 해운대역", "날짜: 2024. 11. 22", "시간: 오전 7시"),
        ApplyList(R.drawable.dummy_image_3, "춘천 같이 가실 분", "출발지: 서울 종로구", "목적지: 춘천", "날짜: 2024. 11. 24", "시간: 오후 3시")
    )

    private val viewModel: CarPoolViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 지원, 작성 내역 선택 모달 창 열기
        binding.listLl.setOnClickListener {
            applyModal(CarpoolApplicationModal())
        }
        // 취소하기 모달 창 열기
        if (binding.carpoolApplyListRv.visibility == View.VISIBLE) {
            // cancel_button이 존재하는지 확인
            val cancelButton = binding.root.findViewById<Button>(R.id.cancel_button)

            // cancelButton이 null이 아니면 클릭 리스너 설정
            cancelButton.setOnClickListener {
                Log.d(TAG, "응 눌리고 있어")
                applyModal(CarpoolCancelModal())
            }
        }


        // 지원한 내역, 작성한 내역 선택 함수
        observeViewModel()
        // Apply List 리사이클러 뷰 어댑터 설정
        setList()
    }

    // 모달 창 열기 함수
    private fun applyModal(modal: BottomSheetDialogFragment) {
        val carpoolModal = modal
        carpoolModal.show(parentFragmentManager, CarpoolApplicationModal.TAG)
    }

    // 지원한 내역, 작성한 내역 선택 함수
    private fun observeViewModel() {
        viewModel.isApplySelected.observe(viewLifecycleOwner) { isSelected ->
            with(binding) {
                if (isSelected == true) {
                    noApplicationTv.visibility = View.INVISIBLE
                    carpoolApplyListRv.visibility = View.VISIBLE
                    carpoolWriteListRv.visibility = View.INVISIBLE
                    applyTv.visibility = View.VISIBLE
                    writeTv.visibility = View.INVISIBLE
                }
            }
        }

        viewModel.isWriteSelected.observe(viewLifecycleOwner) { isSelected ->
            with(binding) {
                if (isSelected == true) {
                    noApplicationTv.visibility = View.INVISIBLE
                    carpoolApplyListRv.visibility = View.INVISIBLE
                    carpoolWriteListRv.visibility = View.VISIBLE
                    applyTv.visibility = View.INVISIBLE
                    writeTv.visibility = View.VISIBLE
                }
            }
        }
    }

    // 작성한 내역 (Apply List) 리사이클러 뷰 어댑터 설정
    private fun setList(){
        val adapter = CarPoolApplyAdapter(itemList, childFragmentManager, viewModel)
        binding.carpoolApplyListRv.apply {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        }
        adapter.setItemClickListener(object : CarPoolApplyAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {

            }
        })
    }
}
