package com.example.waytogo.CarPool

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.example.waytogo.databinding.CustomDialogBinding

class CustomDialog(context: Context, private val message: String) : Dialog(context) {

    private lateinit var binding: CustomDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = LayoutInflater.from(context)
        binding = CustomDialogBinding.inflate(inflater)
        setContentView(binding.root)

        val messageTextView = binding.postUpTv
        val confirmButton = binding.postCheckBtn

        messageTextView.text = message
        confirmButton.setOnClickListener {
            dismiss()
        }
    }
}