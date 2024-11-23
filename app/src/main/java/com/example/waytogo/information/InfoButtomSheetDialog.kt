package com.example.waytogo.information

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentCourseRecommendBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InfoButtomSheetDialog : BottomSheetDialogFragment() {
    private val imageStateMap = mutableMapOf<Int, Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.information_bottom_sd, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 닫기 버튼 설정
        val nextBtn = view.findViewById<ImageView>(R.id.information_nextbtn)
        nextBtn?.setOnClickListener {
            val infoLocationFragment = InfoLocationFragment() // 이동할 Fragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentcontainer_fl, infoLocationFragment) // Container ID에 새 Fragment를 추가
                .addToBackStack(null) // 뒤로가기 스택 추가
                .commit()
            dismiss()
        }

        // EditText와 관련된 로직
        val courseLocationEt = view.findViewById<EditText>(R.id.informationInputbox_et)
        val courseLocationBox = view.findViewById<ImageView>(R.id.informationInputbox_iv)

        if (courseLocationEt != null && courseLocationBox != null) {
            courseLocationEt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.isNullOrEmpty()) {
                        courseLocationBox.setImageResource(R.drawable.information_inputbox_iv)
                    } else {
                        courseLocationBox.setImageResource(R.drawable.information_inputbox_after_iv)
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }

        // 이미지 클릭 토글
        val title1list = listOf(
            Triple(R.id.informationtitle1_box1, R.drawable.coursetitle1_box1, R.drawable.coursetitle1af_box1),
            Triple(R.id.informationTitle1_box2, R.drawable.coursetitle1_box2, R.drawable.coursetitle1af_box2),
            Triple(R.id.informationTitle1_box3, R.drawable.coursetitle1_box3, R.drawable.coursetitle1af_box3),
            Triple(R.id.informationTitle1_box4, R.drawable.coursetitle1_box4, R.drawable.coursetitle1af_box4),
            Triple(R.id.informationTitle1_box5, R.drawable.coursetitle1_box5, R.drawable.coursetitle1af_box5),
            Triple(R.id.informationTitle1_box6, R.drawable.coursetitle1_box6, R.drawable.coursetitle1af_box6)
        )

        title1list.forEach { (id, originalImage, newImage) ->
            val imageView = view.findViewById<ImageView>(id)
            imageView?.setOnClickListener {
                toggleImage(imageView, id, originalImage, newImage)
            }
        }
    }

    private fun toggleImage(imageView: ImageView, id: Int, originalImage: Int, newImage: Int) {
        val isOriginalImage = imageStateMap[id] ?: true

        imageView.setImageResource(if (isOriginalImage) newImage else originalImage)
        imageStateMap[id] = !isOriginalImage
    }
}
