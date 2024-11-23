package com.example.waytogo.CarPool.CarPoolApply

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.waytogo.BaseFragment
import com.example.waytogo.CarPool.CarPoolList.ListData
import com.example.waytogo.CarPool.CarPoolModal.CarpoolApplyModal
import com.example.waytogo.CarPool.CarPoolModal.CarpoolReportModal
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentCarPoolPostBinding
import com.example.waytogo.utils.Constants.TAG
import com.google.gson.Gson

class CarPoolPostFragment : BaseFragment<FragmentCarPoolPostBinding>(FragmentCarPoolPostBinding::inflate) {

    private var isLikeClicked : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // FindListFragment에서 넘어온 데이터 넣어주기
        receiveHomeData()

        // 지원하기 버튼을 누를 경우 Modal 창 띄우기
        binding.applyBtn.setOnClickListener {
            applyCarpoolApplyModal()
        }

        onClickReport()
        onClickLike()

        animationSelection(binding.reportText)
    }

    private var gson: Gson = Gson()

    // 게시글 정보 데이터 수신
    private fun receiveHomeData() {
        /* argument에서 데이터를 꺼내기*/
        // data class 데이터
        val CarpoolInformJson = arguments?.getString("CarpoolInform")
        val postInform = gson.fromJson(CarpoolInformJson, ListData::class.java)
        // 뷰 랜더링
        setInit(postInform)
    }

    // binding해서 각 정보 대입해주기
    private fun setInit(itemList: ListData) {
        // FindListFragment 에서 넘어온 게시글 정보 데이터
        val title = itemList.title
        val destination = itemList.destination
        val date = itemList.date
        val time = itemList.time

        // 값 넣어주기
        with(binding) {
            carpoolPostTitleTv.text = title
            carpoolPostDestinationTv.text = destination
            carpoolPostDateTv.text = date
            carpoolPostTimeTv.text = time
        }
    }

    // Modal 창 띄우기
    private fun applyCarpoolApplyModal(){
        val carpoolModal = CarpoolApplyModal()
        carpoolModal.show(parentFragmentManager, CarpoolApplyModal.TAG)
    }

    // 좋아요 버튼 관리
    private fun onClickLike(){
        val button : ImageButton = binding.heartBtn

        button.setOnClickListener {
            if (isLikeClicked){
                Log.d(TAG, "좋아요 해제\n${isLikeClicked}")
                button.setBackgroundResource(R.drawable.like_unclick_icon)
                isLikeClicked = false
            }
            else {
                Log.d(TAG, "좋아요 등록\n${isLikeClicked}")
                button.setBackgroundResource(R.drawable.like_click_icon)
                isLikeClicked = true
            }
        }
    }

    // 신고하기 버튼 관리
    private fun onClickReport(){
        val button: ImageButton = binding.reportBtn

        button.setOnClickListener {
            applyCarpoolReportModal()
        }
    }

    private fun applyCarpoolReportModal(){
        val carpoolModal = CarpoolReportModal()
        carpoolModal.show(parentFragmentManager, CarpoolReportModal.TAG)
    }

    // 신고하기 텍스트 애니메이션
    private fun animationSelection(imageView: ImageView) {
        val fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f).apply {
            duration = 300
        }

        val fadeOut = ObjectAnimator.ofFloat(imageView, "alpha", 1f, 0f).apply {
            startDelay = 500 // 나타난 후 300ms 대기
            duration = 500
        }

        AnimatorSet().apply {
            playSequentially(fadeIn, fadeOut)
            start()
        }
    }
}