package com.example.waytogo.CarPool

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.waytogo.BaseFragment
import com.example.waytogo.CarPool.CarPoolApply.CarPoolApplyActivity
import com.example.waytogo.CarPool.CarPoolModal.CarpoolModalFragment
import com.example.waytogo.Retrofit.MapViewModel
import com.example.waytogo.Retrofit.RetrofitManager
import com.example.waytogo.Retrofit.SearchDocumentsResponse
import com.example.waytogo.databinding.FragmentFindCarPoolBinding
import com.example.waytogo.utils.API.NATIVE_KEY
import com.example.waytogo.utils.Constants
import com.example.waytogo.utils.Constants.TAG
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.KakaoMapSdk
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.camera.CameraAnimation
import com.kakao.vectormap.camera.CameraUpdateFactory
import kotlinx.coroutines.launch
import java.lang.Exception

class FindCarPoolFragment : BaseFragment<FragmentFindCarPoolBinding>(FragmentFindCarPoolBinding::inflate) {

    private var kakaoMap : KakaoMap? = null
    private val mapViewModel: MapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.departmentEl.setOnClickListener{
            carpoolModal()
        }

        binding.destinationEt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                moveToApplyActivity()
                true // 이벤트 소비 완료
            }
            else {
                false // 이번전 전달
            }
        }

        showMapView()
        setSearch()

    }




    // Modal 창 켜기
    private fun carpoolModal(){
        val carpoolModal = CarpoolModalFragment()
        carpoolModal.show(parentFragmentManager, CarpoolModalFragment.TAGG)
    }

    // 입력된 위치 데이터 넘겨주면서 프래그먼트 이동
    private fun moveToApplyActivity(){
        val startLocation = binding.departmentEt.text.toString()
        val endLocation = binding.destinationEt.text.toString()

//        (activity as CarPoolActivity).changeFragment(CarPoolListFragment().apply {
//            arguments = Bundle().apply {
//                val gson = Gson()
//                putString("department", department)
//                putString("destination", destination)
//            }
//        })
        val intent = Intent(activity, CarPoolApplyActivity::class.java)
        // 데이터 넣기
        intent.apply {
            this.putExtra("startLocation", startLocation)
            this.putExtra("endLocation", endLocation)
        }
        // 화면 이동
        startActivity(intent)
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

    // 지도 검색 API 요청 함수
    private fun setSearch() {
        val department = binding.departmentEt
        val destination = binding.destinationEt

        // 출발지 검색 네트워크 요청 처리
        department.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchText = department.text.toString()

                // 네트워크 요청을 비동기적으로 실행
                lifecycleScope.launch {
                    try {
                        val response = RetrofitManager.instance.searchMaps(searchTerm = searchText)
                        if (response != null && response.isSuccessful) {
//                            Toast.makeText(requireContext(), "Api 호출 성공.", Toast.LENGTH_SHORT).show()
//                            Log.d(TAG, "Api 호출 성공: ${response.body()}")
                            when (response.code()){
                                200 -> {
                                    response.body()?.let {

                                        val parsedLocationValueArray = ArrayList<SearchDocumentsResponse>()
                                        val jsonArray = it.asJsonObject

                                        val document = jsonArray.getAsJsonArray("documents")

                                        // 배열 내부의 각 요소를 처리
                                        for (jsonElement in document) {
                                            val jsonObject = jsonElement.asJsonObject
                                            val x = jsonObject.get("x").asDouble
                                            val y = jsonObject.get("y").asDouble

                                            // 데이터 모델로 매핑
                                            val locationValue = SearchDocumentsResponse(
                                                x = x,
                                                y = y
                                            )
                                            parsedLocationValueArray.add(locationValue)
                                        }

                                        Log.d(TAG, "parsedLocationValueArray : ${parsedLocationValueArray[0].x}")
                                        Log.d(TAG, "KAKAO_MAP : ${kakaoMap}")

                                        // kakaoMap 확인 후 사용
                                        kakaoMap?.let { map ->
                                            // 지도 좌표값으로 포커싱 변경
                                            val x = parsedLocationValueArray[0].x
                                            val y = parsedLocationValueArray[0].y
                                            val position = LatLng.from(y, x)

                                            val camera = CameraUpdateFactory.newCenterPosition(position) // 이동할 위치 설정
                                            map.moveCamera(camera, CameraAnimation.from(500, true, true))
                                        } ?: run {
                                            // kakaoMap이 null일 경우
                                            Toast.makeText(requireContext(), "지도 로딩 중입니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            }



                        } else {
                            Toast.makeText(requireContext(), "Api 호출 실패", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        // 예외 처리
                        Toast.makeText(requireContext(), "지명을 정확히 입력해주세요", Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "네트워크 오류: ${e.message}")
                    }
                }

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        // 목적지 검색 네트워크 요청 처리
        destination.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchText = destination.text.toString()

                // 네트워크 요청을 비동기적으로 실행
                lifecycleScope.launch {
                    try {
                        val response = RetrofitManager.instance.searchMaps(searchTerm = searchText)
                        if (response != null && response.isSuccessful) {
                            Toast.makeText(requireContext(), "Api 호출 성공.", Toast.LENGTH_SHORT).show()
                            Log.d(TAG, "Api 호출 성공: ${response.body()}")
                        } else {
                            Toast.makeText(requireContext(), "Api 호출 실패", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        // 예외 처리
                        Toast.makeText(requireContext(), "네트워크 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
                        Log.e(TAG, "네트워크 오류: ${e.message}")
                    }
                }

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

}