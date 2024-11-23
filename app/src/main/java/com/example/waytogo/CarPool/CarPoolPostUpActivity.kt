package com.example.waytogo.CarPool

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.waytogo.R
import com.example.waytogo.databinding.ActivityCarPoolPostUpBinding
import java.util.Calendar

class CarPoolPostUpActivity : AppCompatActivity() {
    private var startDate: Calendar? = null
    private var endDate: Calendar? = null

    private lateinit var binding: ActivityCarPoolPostUpBinding
    private lateinit var photoPickerLauncher: ActivityResultLauncher<Intent>
    private val maxPhotos = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarPoolPostUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val photoUploadButton = binding.photoUploadIb
        val photoContainer = binding.photoContainer

        // Activity Result Launcher 등록
        photoPickerLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.data?.let { uri ->
                    addPhotoToContainer(uri)
                }
            }
        }

        // 사진 업로드 버튼 클릭 리스너
        photoUploadButton.setOnClickListener {
            if (photoContainer.childCount < maxPhotos) {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                photoPickerLauncher.launch(intent)
            } else {
                Toast.makeText(this, "최대 4장까지 추가할 수 있습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 이미지 URI를 PhotoContainer에 추가
    private fun addPhotoToContainer(uri: Uri) {
        val photoContainer = binding.photoContainer

        // 새로운 이미지 추가
        val imageView = ImageView(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.photo_width),
                resources.getDimensionPixelSize(R.dimen.photo_height)
            ).apply {
                marginEnd = resources.getDimensionPixelSize(R.dimen.photo_margin)
            }
            setImageURI(uri)
            scaleType = ImageView.ScaleType.CENTER_CROP
        }
        photoContainer.addView(imageView, photoContainer.childCount)

        // 최대 사진 갯수 확인 후 버튼 숨기기
        if (photoContainer.childCount >= maxPhotos) {
            binding.photoUploadIb.visibility = View.GONE
        }




        // DatePicker
        val startDateButton = binding.startDateBtn
        val endDateButton = binding.endDateBtn
        startDateButton.setOnClickListener {
            showDatePickerDialog { year, month, day ->
                // 시작일 선택
                startDate = Calendar.getInstance().apply {
                    set(year, month, day)
                }
                // 시작 버튼 텍스트 업데이트
                startDateButton.text = formatDateString(year, month, day)
                validateDates(startDateButton, endDateButton)
            }
        }

        endDateButton.setOnClickListener {
            showDatePickerDialog { year, month, day ->
                // 종료일 선택
                endDate = Calendar.getInstance().apply {
                    set(year, month, day)
                }
                // 종료 버튼 텍스트 업데이트
                endDateButton.text = formatDateString(year, month, day)
                validateDates(startDateButton, endDateButton)
            }
        }

        // TimePicker
        val startTimeButton = binding.startTimeBtn
        val endTimeButton = binding.endTimeBtn

        startTimeButton.setOnClickListener {
            CustomTimePickerDialog(this) { hour, minute ->
                startTimeButton.text = formatTimeString(hour, minute)
            }.show()
        }

        endTimeButton.setOnClickListener {
            CustomTimePickerDialog(this) { hour, minute ->
                endTimeButton.text = formatTimeString(hour, minute)
            }.show()
        }

        binding.postUpBtn.setOnClickListener {
            val intent = Intent(this, CarPoolActivity::class.java)
            intent.putExtra("showDialog", true) // 다이얼로그 표시 여부 전달
            startActivity(intent)
        }
    }


    // DatePickerDialog 띄우기
    private fun showDatePickerDialog(onDateSelected: (Int, Int, Int) -> Unit) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                onDateSelected(selectedYear, selectedMonth, selectedDay)
            },
            year, month, day
        ).show()
    }

    // 날짜를 "yyyy-MM-dd" 형식으로 변환
    private fun formatDateString(year: Int, month: Int, day: Int): String {
        return "${year}년 ${month + 1}월 ${day}일"
    }

    // 날짜 유효성 검사 및 버튼 상태 업데이트
    private fun validateDates(startButton: Button, endButton: Button) {
        if (startDate != null && endDate != null && startDate!!.after(endDate)) {
            startButton.text = "시작일 다시 선택"
            endButton.text = "종료일 다시 선택"
            Toast.makeText(this, "시작일은 종료일보다 이전이어야 합니다.", Toast.LENGTH_SHORT).show()
        }
    }


    // 시간을 "오전 hh:mm" 또는 "오후 hh:mm" 형식으로 변환
    private fun formatTimeString(hourOfDay: Int, minute: Int): String {
        val isAM = hourOfDay < 12
        val hour = if (hourOfDay % 12 == 0) 12 else hourOfDay % 12 // 0시 또는 12시 처리
        val amPm = if (isAM) "오전" else "오후"
        return String.format("%s %d:%02d", amPm, hour, minute)
    }
//
//    // 시간 유효성 검사 및 버튼 상태 업데이트
//    private fun validateTimes(startButton: Button, endButton: Button) {
//        if (startTime != null && endTime != null && startTime!!.after(endTime)) {
//            startButton.text = "시작 시간 다시 선택"
//            endButton.text = "종료 시간 다시 선택"
//            Toast.makeText(this, "시작 시간은 종료 시간보다 이전이어야 합니다.", Toast.LENGTH_SHORT).show()
//        }
//    }
}