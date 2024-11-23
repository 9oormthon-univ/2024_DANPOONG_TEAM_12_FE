package com.example.waytogo.CarPool.CarPoolModal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.waytogo.CarPool.CarPoolApplication.CarPoolViewModel
import com.example.waytogo.databinding.CarpoolCancelModalLayoutBinding
import com.example.waytogo.databinding.CarpoolReportModalBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CarpoolReportModal() : BottomSheetDialogFragment() {
    private lateinit var binding: CarpoolReportModalBinding

    companion object {
        const val TAG = "CarpoolReportModal"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = CarpoolReportModalBinding.inflate(inflater, container, false)

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
        val carpoolModal = CarpoolReportCheckModal()
        carpoolModal.show(parentFragmentManager, CarpoolReportCheckModal.TAG)
    }
}

