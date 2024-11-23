package com.example.waytogo.Course

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentCourseSaveBinding

class CourseSaveFragment : Fragment() {
    private var _binding : FragmentCourseSaveBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCourseSaveBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.courseSaveSavebtn.setOnClickListener {
            showConfirmationDialog(requireContext())
        }
    }

    fun showConfirmationDialog(context : Context) {
        val dialog = AlertDialog.Builder(context).setTitle("코스 추천 저장")
            .setMessage("추천 코스가 저장되었습니다.\n저장된 코스는 내코스에서 확인 가능합니다.")
            .setPositiveButton("확인") { dialog, _ ->
                dialog.dismiss() //다이얼로그 닫기
            }.create()
        dialog.show()

        // 다이얼로그 버튼의 위치를 조정 (아래로 기본적으로 배치된 버튼을 중앙으로 이동)
        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        val layoutParams = positiveButton.layoutParams as LinearLayout.LayoutParams
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL
        positiveButton.layoutParams = layoutParams
    }

//    class CustomDialog(context: Context, private val message: String) : Dialog(context) {
//
//        private lateinit var binding: CustomDialogBinding
//
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            val inflater = LayoutInflater.from(context)
//            binding = CustomDialogBinding.inflate(inflater)
//            setContentView(binding.root)
//
//            val messageTextView = binding.postUpTv
//            val confirmButton = binding.postCheckBtn
//
//            messageTextView.text = message
//            confirmButton.setOnClickListener {
//                dismiss()
//            }
//        }
}