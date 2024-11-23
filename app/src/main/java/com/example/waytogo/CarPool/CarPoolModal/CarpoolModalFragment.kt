package com.example.waytogo.CarPool.CarPoolModal

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.waytogo.CarPool.FindCarPoolFragment
import com.example.waytogo.R
import com.example.waytogo.Retrofit.MapViewModel
import com.example.waytogo.Retrofit.RetrofitManager
import com.example.waytogo.Retrofit.SearchDocumentsResponse
import com.example.waytogo.databinding.CarpoolModalLayoutBinding
import com.example.waytogo.databinding.FragmentFindCarPoolBinding
import com.example.waytogo.utils.API.NATIVE_KEY
import com.example.waytogo.utils.Constants.TAG
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.KakaoMapSdk
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.camera.CameraAnimation
import com.kakao.vectormap.camera.CameraUpdateFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Exception

@AndroidEntryPoint
class CarpoolModalFragment() : BottomSheetDialogFragment() {
    private lateinit var _binding : CarpoolModalLayoutBinding
    private lateinit var binding : FragmentFindCarPoolBinding
    private var kakaoMap : KakaoMap? = null

    private val mapViewModel: MapViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = CarpoolModalLayoutBinding.inflate(inflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object{
        const val TAGG = "CarpoolModal"
    }


}