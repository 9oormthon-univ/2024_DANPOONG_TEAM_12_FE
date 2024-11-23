package com.example.waytogo.Course

import android.animation.ObjectAnimator
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentCourseRecommendBinding

class CourseRecommendFragment : Fragment() {
    private var _binding : FragmentCourseRecommendBinding? = null
    private val binding get() = _binding!!

    private val personList = listOf("가족","친구","형제","자매","혼자")
    private var currentIndex = 1

    private val imageStateMap = mutableMapOf<Int, Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseRecommendBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.courseNextbtn.setOnClickListener {
            val coursesettingfragment = CourseSettingFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.course_child_fl, coursesettingfragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        binding.coursewhoafTv.text = personList[currentIndex]


        binding.courseleftBtn.setOnClickListener {
            if(currentIndex > 0) {
                currentIndex--
                animationSelection(binding.coursewhoafTv)
                binding.coursewhoafTv.setText(personList[currentIndex])
            }
        }
        binding.courserightBtn.setOnClickListener {
            if(currentIndex < personList.size -1) {
                currentIndex++
                animationSelection(binding.coursewhoafTv)
                binding.coursewhoafTv.setText(personList[currentIndex])
            }
        }

        binding.courseLocationEt.setOnClickListener {
            binding.coursesublistCl.visibility = View.VISIBLE
        }
        binding.courseLocationBox.setOnClickListener {
            //gone된 이미지를 보이게 변경
            binding.coursesublistCl.visibility = View.VISIBLE
        }

        //edittext 입력을 계속 주시 (textwatcher)
        binding.courseLocationEt.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            //텍스트 입력시 변경
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.isNullOrEmpty()){
                    //edittext내에 값이 없으면 원래 이미지로 변경
                    binding.courseLocationBox.setImageResource(R.drawable.courselocation_box)
                }else {
                    //edittext내에 값이 있으면 이미지 변경
                    binding.courseLocationBox.setImageResource(R.drawable.courselocationaf_box)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        //edittext 입력을 계속 주시 (textwatcher)
        binding.courseDateEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            //텍스트 입력시 변경
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.isNullOrEmpty()){
                    //edittext내에 값이 없으면 원래 이미지로 변경
                    binding.courseDateBox.setImageResource(R.drawable.coursedate_box)
                }else {
                    //edittext내에 값이 있으면 이미지 변경
                    binding.courseDateBox.setImageResource(R.drawable.coursedateaf_box)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        val title1list = listOf(
            Triple(R.id.coursetitle1_box1,R.drawable.coursetitle1_box1,R.drawable.coursetitle1af_box1),
            Triple(R.id.courseTitle1_box2,R.drawable.coursetitle1_box2,R.drawable.coursetitle1af_box2),
            Triple(R.id.courseTitle1_box3,R.drawable.coursetitle1_box3,R.drawable.coursetitle1af_box3),
            Triple(R.id.courseTitle1_box4,R.drawable.coursetitle1_box4,R.drawable.coursetitle1af_box4),
            Triple(R.id.courseTitle1_box5,R.drawable.coursetitle1_box5,R.drawable.coursetitle1af_box5),
            Triple(R.id.courseTitle1_box6,R.drawable.coursetitle1_box6,R.drawable.coursetitle1af_box6)
        )
        val title2list = listOf(
            Triple(R.id.courseTitle2_box1,R.drawable.coursetitle2_box1,R.drawable.coursetitle2af_box1),
            Triple(R.id.courseTitle2_box2,R.drawable.coursetitle2_box2,R.drawable.coursetitle2af_box2),
            Triple(R.id.courseTitle2_box3,R.drawable.coursetitle2_box3,R.drawable.coursetitle2af_box3),
            Triple(R.id.courseTitle2_box4,R.drawable.coursetitle2_box4,R.drawable.coursetitle2af_box4),
            Triple(R.id.courseTitle2_box5,R.drawable.coursetitle2_box5,R.drawable.coursetitle2af_box5),
            Triple(R.id.courseTitle2_box6,R.drawable.coursetitle2_box6,R.drawable.coursetitle2af_box6)
        )


        title1list.forEach { (id,_,_) -> imageStateMap[id] = true }
        title2list.forEach { (id,_,_) -> imageStateMap[id] = true }

        title1list.forEach { (id, originalImage, newImage) ->
            view.findViewById<ImageView>(id).setOnClickListener { imageView ->
                toggleImage(imageView as ImageView, id, originalImage, newImage)
            }
        }
        title2list.forEach { (id, originalImage, newImage) ->
            view.findViewById<ImageView>(id).setOnClickListener { imageView ->
                toggleImage(imageView as ImageView, id, originalImage, newImage)
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun toggleImage (imageView: ImageView, id: Int, originalImage: Int, newImage: Int) {
        val isOriginalImage = imageStateMap[id] ?: true

        if (isOriginalImage) {
            // 변경된 이미지로 설정
            imageView.setImageResource(newImage)
        } else {
            // 원래 이미지로 복원
            imageView.setImageResource(originalImage)
        }

        // 이미지 상태 변경
        imageStateMap[id] = !isOriginalImage
    }

}
private fun animationSelection(textView: TextView) {
    val animator = ObjectAnimator.ofFloat(textView,"alpha",0f,1f)
    animator.duration = 300
    animator.start()
}