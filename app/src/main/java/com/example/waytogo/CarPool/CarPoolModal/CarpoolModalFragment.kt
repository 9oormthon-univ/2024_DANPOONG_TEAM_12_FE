package com.example.waytogo.CarPool.CarPoolModal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.waytogo.databinding.CarpoolModalLayoutBinding
import com.example.waytogo.utils.API.NATIVE_KEY
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.KakaoMapSdk
import com.kakao.vectormap.MapLifeCycleCallback
import java.lang.Exception

class CarpoolModalFragment() : BottomSheetDialogFragment() {
    private lateinit var binding : CarpoolModalLayoutBinding
    private var kakaoMap : KakaoMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = CarpoolModalLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showMapView()
    }

    companion object{
        const val TAG = "CarpoolModal"
    }

    // 카카오맵 켜기
    private fun showMapView(){

        val mapView = binding.mapView

        // KakaoMap SDK 초기화
        KakaoMapSdk.init(requireContext(), NATIVE_KEY)

        mapView.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {
                // 지도 API가 정상적으로 종료될 때 실행
                Log.d(TAG, "onMapDestroy")
            }

            override fun onMapError(p0: Exception?) {
                // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출
                Log.d(TAG, "onMapError")
            }

        }, object : KakaoMapReadyCallback() {
            override fun onMapReady(kakaomap: KakaoMap) {
                // 정상적으로 인증이 완료되었을 때 호출
                kakaoMap = kakaomap
            }

        })
    }
}