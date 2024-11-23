package com.example.waytogo.Course

import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentCourseBinding

class CourseFragment : Fragment() {
    private var _binding : FragmentCourseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCourseBinding.inflate(inflater,container,false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recommendfragment = CourseRecommendFragment()
        val mycoursefragment = CourseMycourseFragment()

        // 초기화면 세팅
        if(childFragmentManager.findFragmentById(R.id.course_child_fl)==null)
            childFragmentManager.beginTransaction().replace(R.id.course_child_fl,recommendfragment).commit()

        binding.courseRecommendTv.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.course_child_fl,recommendfragment).commit()
        }
        binding.courseMycourseTv.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.course_child_fl,mycoursefragment).commit()
        }

    }
}