package com.example.waytogo.information

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentInfoBinding
import com.example.waytogo.databinding.FragmentInfoLocationBinding

class InfoLocationFragment : Fragment() {
    private var _binding : FragmentInfoLocationBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoLocationBinding.inflate(inflater,container,false)
        return binding.root
    }


}