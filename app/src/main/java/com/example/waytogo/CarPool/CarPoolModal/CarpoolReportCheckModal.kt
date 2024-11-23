package com.example.waytogo.CarPool.CarPoolModal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.waytogo.CarPool.CarPoolApplication.CarPoolViewModel
import com.example.waytogo.databinding.CarpoolReportModalBinding
import com.example.waytogo.databinding.ReportDoubleCheckModalBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CarpoolReportCheckModal() : BottomSheetDialogFragment() {
    private lateinit var binding: ReportDoubleCheckModalBinding


    companion object {
        const val TAG = "CarpoolReportCheckModal"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = ReportDoubleCheckModalBinding.inflate(inflater, container, false)

        // CarpoolReportModal이 이미 표시 중인 경우 닫기
        val existingFragment = parentFragmentManager.findFragmentByTag(CarpoolReportModal.TAG)
        if (existingFragment is CarpoolReportModal) {
            existingFragment.dismiss()
        }

        binding.cancelButton.setOnClickListener {
            this.dismiss()
        }

        return binding.root
    }

}

