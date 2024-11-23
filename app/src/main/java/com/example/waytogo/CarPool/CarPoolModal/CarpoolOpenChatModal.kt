package com.example.waytogo.CarPool.CarPoolModal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waytogo.databinding.CarpoolOpenChatModalLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CarpoolOpenChatModal() : BottomSheetDialogFragment() {
    private lateinit var binding : CarpoolOpenChatModalLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = CarpoolOpenChatModalLayoutBinding.inflate(inflater, container, false)

        // CarpoolApplyModal이 이미 표시 중인 경우 닫기
        val existingFragment = parentFragmentManager.findFragmentByTag(CarpoolApplyModal.TAG)
        if (existingFragment is CarpoolApplyModal) {
            existingFragment.dismiss()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object{
        const val TAG = "CarpoolOpenChatModal"
    }

}