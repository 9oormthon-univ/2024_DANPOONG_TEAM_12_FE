package com.example.waytogo.Course

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentCourseBinding
import com.example.waytogo.databinding.FragmentCourseSettingBinding
import java.util.Calendar

class CourseSettingFragment : Fragment() {
    private var _binding : FragmentCourseSettingBinding? = null
    private val binding get() = _binding!!

    private var startTimeInMillis: Long = 0
    private var endTimeInMillis: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCourseSettingBinding.inflate(inflater,container,false)
        return binding.root

        // 시작 시간 선택
        binding.courseSettingFirstStartTimeIv.setOnClickListener {
            showTimePicker { hour, minute ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                startTimeInMillis = calendar.timeInMillis
                binding.courseSettingFirstStartTimeTv.text = formatTime(hour, minute)
                calculateTotalTime()
            }
        }

        // 종료 시간 선택
        binding.courseSettingFirstEndTimeIv.setOnClickListener {
            showTimePicker { hour, minute ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                endTimeInMillis = calendar.timeInMillis
                binding.courseSettingFirstEndTimeTv.text = formatTime(hour, minute)
                calculateTotalTime()
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mapcheck = true

        //설정완료 버튼 누르면 맵과 시간 보여줌
        binding.courseSettingNextbtn.setOnClickListener {
            binding.courseSettingMapbtn.visibility = View.VISIBLE
            binding.courseSettingMapbtn.setImageResource(R.drawable.course_setting_downbtn)
            binding.courseSettingMapiv.visibility = View.VISIBLE
            binding.courseSettingFinishbtn.visibility = View.VISIBLE
            binding.courseSettingNextbtn.visibility = View.GONE
            binding.courseSettingLine.visibility = View.GONE
        }

        //지도 on/off
        binding.courseSettingMapbtn.setOnClickListener {
            if(mapcheck) {
                binding.courseSettingMapiv.visibility = View.GONE
                binding.courseSettingMapbtn.setImageResource(R.drawable.course_setting_upbtn)
                mapcheck = false
            } else {
                binding.courseSettingMapiv.visibility = View.VISIBLE
                binding.courseSettingMapbtn.setImageResource(R.drawable.course_setting_downbtn)
                mapcheck = true
            }
        }

        val savafragment = CourseSaveFragment()
        binding.courseSettingFinishbtn.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.course_child_fl,savafragment).commit()
        }

    }

    private fun showTimePicker(onTimeSet: (hour: Int, minute: Int) -> Unit) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
            onTimeSet(selectedHour, selectedMinute)
        }, hour, minute, true).show()
    }

    private fun formatTime(hour: Int, minute: Int): String {
        return String.format("%02d:%02d", hour, minute)
    }

    private fun calculateTotalTime() {
        if (startTimeInMillis != 0L && endTimeInMillis != 0L) {
            val diffInMillis = endTimeInMillis - startTimeInMillis
            if (diffInMillis > 0) {
                val hours = diffInMillis / (1000 * 60 * 60)
                val minutes = (diffInMillis / (1000 * 60)) % 60
                binding.courseSetting1Tv.setText("여행시간 총 ${hours}시간 ${minutes}분")
            } else {
                Toast.makeText(requireContext(), "종료 시간이 시작 시간보다 이후여야 합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}