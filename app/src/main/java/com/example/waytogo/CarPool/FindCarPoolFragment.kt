package com.example.waytogo.CarPool

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.example.waytogo.BaseFragment
import com.example.waytogo.CarPool.CarPoolApply.CarPoolApplyActivity
import com.example.waytogo.CarPool.CarPoolModal.CarpoolModalFragment
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentFindCarPoolBinding
import com.google.gson.Gson

class FindCarPoolFragment : BaseFragment<FragmentFindCarPoolBinding>(FragmentFindCarPoolBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.departmentEl.setOnClickListener{
            carpoolModal()
        }

        binding.destinationEt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                moveToApplyActivity()
                true // 이벤트 소비 완료
            }
            else {
                false // 이번전 전달
            }
        }
    }

    // Modal 창 켜기
    private fun carpoolModal(){
        val carpoolModal = CarpoolModalFragment()
        carpoolModal.show(parentFragmentManager, CarpoolModalFragment.TAG)
    }

    // 입력된 위치 데이터 넘겨주면서 프래그먼트 이동
    private fun moveToApplyActivity(){
        val department = binding.departmentEt.text.toString()
        val destination = binding.destinationEt.text.toString()

//        (activity as CarPoolActivity).changeFragment(CarPoolListFragment().apply {
//            arguments = Bundle().apply {
//                val gson = Gson()
//                putString("department", department)
//                putString("destination", destination)
//            }
//        })
        val intent = Intent(activity, CarPoolApplyActivity::class.java)
        // 데이터 넣기
        intent.apply {
            this.putExtra("department", department)
            this.putExtra("destination", destination)
        }
        // 화면 이동
        startActivity(intent)
    }

}