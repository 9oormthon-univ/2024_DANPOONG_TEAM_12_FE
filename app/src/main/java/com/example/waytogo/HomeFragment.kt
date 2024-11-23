package com.example.waytogo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.waytogo.CarPool.CarPoolActivity
import com.example.waytogo.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val imageStateMap = mutableMapOf<Int, Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homecarfoolBtn.setOnClickListener {
            val intent = Intent(requireContext(), CarPoolActivity::class.java)
            startActivity(intent)
        }

        val likelist = listOf(
            Triple(R.id.hometitle2_frame1_heartbf_iv,R.drawable.homeheartbf_iv,R.drawable.homeheartaf_iv),
            Triple(R.id.hometitle2_frame2_heartbf_iv,R.drawable.homeheartbf_iv,R.drawable.homeheartaf_iv),
            Triple(R.id.hometitle2_frame3_heartbf_iv,R.drawable.homeheartbf_iv,R.drawable.homeheartaf_iv),
            Triple(R.id.hometitle2_frame4_heartbf_iv,R.drawable.homeheartbf_iv,R.drawable.homeheartaf_iv),
            Triple(R.id.hometitle2_frame5_heartbf_iv,R.drawable.homeheartbf_iv,R.drawable.homeheartaf_iv),
            Triple(R.id.hometitle2_frame6_heartbf_iv,R.drawable.homeheartbf_iv,R.drawable.homeheartaf_iv),
        )

        likelist.forEach { (id,_,_) -> imageStateMap[id] = true}

        likelist.forEach { (id, originalImage, newImage) ->
            view.findViewById<ImageView>(id).setOnClickListener { imageView ->
                toggleImage(imageView as ImageView, id, originalImage, newImage)
            }
        }
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