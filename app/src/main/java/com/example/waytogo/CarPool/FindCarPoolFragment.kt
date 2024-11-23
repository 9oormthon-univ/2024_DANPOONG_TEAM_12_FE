package com.example.waytogo.CarPool

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Icon
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.waytogo.BaseFragment
import com.example.waytogo.CarPool.CarPoolApply.CarPoolApplyActivity
import com.example.waytogo.CarPool.CarPoolModal.CarpoolModalFragment
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentFindCarPoolBinding
import com.example.waytogo.utils.API.NATIVE_KEY
import com.example.waytogo.utils.Constants
import com.example.waytogo.utils.Constants.TAG
import com.example.waytogo.utils.onMyTextChanged
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.KakaoMapSdk
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.RoadViewRequest.Marker
import com.kakao.vectormap.camera.CameraAnimation
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.CompetitionType
import com.kakao.vectormap.label.CompetitionUnit
import com.kakao.vectormap.label.LabelLayerOptions
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kakao.vectormap.label.LabelTextStyle
import com.kakao.vectormap.label.OrderingType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.Locale

@AndroidEntryPoint
class FindCarPoolFragment :
    BaseFragment<FragmentFindCarPoolBinding>(FragmentFindCarPoolBinding::inflate) {

    private var kakaoMap: KakaoMap? = null
    private val viewModel: FindCarPoolViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onEditTextUIChange()
        showMapView()
        setSearch()
        setMapLocation()

        binding.findBtn.setOnClickListener {
            moveToApplyActivity()
        }

        viewModel.toast.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

    }


    // Modal 창 켜기
    private fun carpoolModal() {
        val carpoolModal = CarpoolModalFragment()
        carpoolModal.show(parentFragmentManager, CarpoolModalFragment.TAGG)
    }

    // 입력된 위치 데이터 넘겨주면서 프래그먼트 이동
    private fun moveToApplyActivity() {
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
    private fun showMapView() {

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

    // xpr
    private fun onEditTextUIChange() {
        binding.departmentEt.onMyTextChanged {
            if (it.toString() != "") {
                binding.departmentEt.setBackgroundResource(R.drawable.interest_select_button)
                binding.editTextDepartmentLogoIv.setImageResource(R.drawable.search_ing_logo)
            } else {
                binding.departmentEt.setBackgroundResource(R.drawable.search_edittext_background)
                binding.editTextDepartmentLogoIv.setImageResource(R.drawable.search_logo)
            }
        }
        binding.destinationEt.onMyTextChanged {
            if (it.toString() != "") {
                binding.destinationEt.setBackgroundResource(R.drawable.interest_select_button)
                binding.editTextDestinationLogoIv.setImageResource(R.drawable.search_ing_logo)
            } else {
                binding.destinationEt.setBackgroundResource(R.drawable.search_edittext_background)
                binding.editTextDestinationLogoIv.setImageResource(R.drawable.search_logo)
            }
        }
    }

    // 지도 검색 API 요청 함수
    private fun setSearch() {
        val department = binding.departmentEt
        val destination = binding.destinationEt

        // 출발지 검색 네트워크 요청 처리
        department.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchText = department.text.toString()

                // 네트워크 요청을 비동기적으로 실행
                viewModel.search(text = searchText)

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }

        // 출발지 검색 네트워크 요청 처리
        destination.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchText = destination.text.toString()

                // 네트워크 요청을 비동기적으로 실행
                viewModel.search(text = searchText)

                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun setMapLocation() {
        // viewModel 사용해서 네트워크 통신 후 지도 위치 이동
        viewModel.searchPositions.observe(viewLifecycleOwner) { positions ->
            try {
                // positions가 비어 있으면 예외 발생
                if (positions.isEmpty()) {
                    throw IllegalArgumentException("지명을 입력하세요")
                }

                val x = positions[0].x
                val y = positions[0].y

                val position = LatLng.from(y, x)

                kakaoMap?.let { map ->
                    val labelLayer = map.labelManager?.addLayer(
                        LabelLayerOptions.from("myLayerId")
                            .setOrderingType(OrderingType.Rank)
                            .setCompetitionUnit(CompetitionUnit.IconAndText)
                            .setCompetitionType(CompetitionType.All)
                    )

                    val labelStyle = LabelStyle.from(R.drawable.marker_icon) // 마커 아이콘 (drawable 리소스)

                    val labelOptions = LabelOptions.from(position)
                        .setStyles(LabelStyles.from(labelStyle))
                    labelLayer?.addLabel(labelOptions)

                    val camera = CameraUpdateFactory.newCenterPosition(position)
                    map.moveCamera(camera, CameraAnimation.from(1000, true, true))
                }
            } catch (e: Exception) {
                // 예외 처리: 위치 값이 없거나 잘못된 경우
                Toast.makeText(requireContext(), "지명을 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }
    }


}