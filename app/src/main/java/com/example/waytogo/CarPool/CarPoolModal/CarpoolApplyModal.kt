package com.example.waytogo.CarPool.CarPoolModal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waytogo.CarPool.CarPoolModal.CarpoolOpenChatModal.Companion
import com.example.waytogo.databinding.CarpoolApplyModalLayoutBinding
import com.example.waytogo.databinding.CarpoolModalLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CarpoolApplyModal() : BottomSheetDialogFragment() {
    private lateinit var binding : CarpoolApplyModalLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = CarpoolApplyModalLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.applyBtn.setOnClickListener {
            applyModal()
        }

    }
    // Modal 창 띄우기
    private fun applyModal(){
        val carpoolModal = CarpoolOpenChatModal()
        carpoolModal.show(parentFragmentManager, CarpoolOpenChatModal.TAG)
    }

    companion object{
        const val TAG = "CarpoolApplyModal"
    }

}