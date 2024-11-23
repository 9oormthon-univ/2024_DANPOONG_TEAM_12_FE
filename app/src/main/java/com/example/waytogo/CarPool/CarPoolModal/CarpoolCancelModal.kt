package com.example.waytogo.CarPool.CarPoolModal

import com.example.waytogo.databinding.CarpoolCancelModalLayoutBinding
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.waytogo.CarPool.CarPoolApplication.CarPoolViewModel
import com.example.waytogo.databinding.CarpoolApplicationModalLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CarpoolCancelModal : BottomSheetDialogFragment() {
    private lateinit var binding: CarpoolCancelModalLayoutBinding

    val viewModel: CarPoolViewModel by activityViewModels()

    companion object {
        const val TAG = "CarpoolApplicationModal"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CarpoolCancelModalLayoutBinding.inflate(inflater, container, false)

//        binding.cancelTitleTv.text = viewModel.applyTitle.toString()
        binding.cancelTitleTv.text = "바다보러 해운대 갑시다!"

        return binding.root
    }
}
