package com.example.waytogo.CarPool.CarPoolApply

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waytogo.BaseFragment
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentFindListBinding
import com.google.gson.Gson

class FindListFragment : BaseFragment<FragmentFindListBinding>(FragmentFindListBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun setInit() {

        val gson = Gson()
        // argument에서 데이터를 꺼내기
        val department = arguments?.getString("department")
        val destination = arguments?.getString("destination")

        with(binding){

        }
    }

}