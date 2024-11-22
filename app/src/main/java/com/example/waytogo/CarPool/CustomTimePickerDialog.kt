package com.example.waytogo.CarPool

import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.NumberPicker
import com.example.waytogo.R
import com.example.waytogo.databinding.DialogTimePickerBinding

class CustomTimePickerDialog(
    context: Context,
    private val onTimeSelected: (Int, Int) -> Unit
) : AlertDialog(context) {

    private var selectedHour: Int = 0
    private var selectedMinute: Int = 0

    init {
        val inflater = LayoutInflater.from(context)
        val binding = DialogTimePickerBinding.inflate(inflater)

        val hourPicker = binding.hourPicker
        val minutePicker = binding.minutePicker
        hourPicker.wrapSelectorWheel = true
        minutePicker.wrapSelectorWheel = true

        // 시간 설정 (0 ~ 23)
        hourPicker.minValue = 0
        hourPicker.maxValue = 23
        hourPicker.value = selectedHour
        hourPicker.setOnValueChangedListener { _, _, newValue -> selectedHour = newValue }

        // 분 설정 (0 ~ 59)
        minutePicker.minValue = 0
        minutePicker.maxValue = 59
        minutePicker.value = selectedMinute
        minutePicker.setOnValueChangedListener { _, _, newValue -> selectedMinute = newValue }

        setView(binding.root)

        setButton(BUTTON_POSITIVE, "확인") { _, _ ->
            onTimeSelected(selectedHour, selectedMinute)
        }
        setButton(BUTTON_NEGATIVE, "취소") { _, _ -> }
    }
}