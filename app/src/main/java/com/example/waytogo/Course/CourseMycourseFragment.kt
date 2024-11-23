package com.example.waytogo.Course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentCourseRecommendBinding

class CourseMycourseFragment : Fragment() {
    private var _binding: FragmentCourseRecommendBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCourseRecommendBinding.inflate(inflater, container, false)
        return binding.root
    }

}