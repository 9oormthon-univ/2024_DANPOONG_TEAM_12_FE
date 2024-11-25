package com.example.waytogo.CarPool.CarPoolModal


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.waytogo.CarPool.CarPoolActivity
import com.example.waytogo.CarPool.CarPoolApplication.CarPoolApplicationFragment
import com.example.waytogo.CarPool.CarPoolApplication.CarPoolViewModel
import com.example.waytogo.databinding.CarpoolApplicationModalLayoutBinding
import com.example.waytogo.databinding.FragmentCarPoolApplicationBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CarpoolApplicationModal : BottomSheetDialogFragment() {
    private lateinit var binding: CarpoolApplicationModalLayoutBinding

    val viewModel: CarPoolViewModel by activityViewModels()

    companion object {
        const val TAG = "CarpoolApplicationModal"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CarpoolApplicationModalLayoutBinding.inflate(inflater, container, false)

        binding.applicationListTv.setOnClickListener {
            viewModel.isApplySelected.value = true
            viewModel.isWriteSelected.value = false
            dismiss()
        }
        binding.writeListTv.setOnClickListener {
            viewModel.isApplySelected.value = false
            viewModel.isWriteSelected.value = true
            dismiss()
        }

        return binding.root
    }
}
