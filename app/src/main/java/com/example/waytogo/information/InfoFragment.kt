package com.example.waytogo.information

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.waytogo.R
import com.example.waytogo.databinding.FragmentInfoBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InfoFragment : Fragment() {
    private var _binding : FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView : RecyclerView = view.findViewById(R.id.informationMain1_rv)

        val items = listOf(
            InformationItem(R.drawable.information_testimg1,"서울","현대백화점 더현대 서울"),
            InformationItem(R.drawable.information_testimg2,"서울","롯데월드 어드벤처")
        )
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = InformationSlideAdapter(items)

        val recyclerView2 : RecyclerView = view.findViewById(R.id.informationMain2_rv)

        val items2 = listOf(
            InformationItem(R.drawable.information_testimg3,"용인","한국 민속촌"),
            InformationItem(R.drawable.information_testimg4,"울산","장생포 고래 문화마당")
        )
        recyclerView2.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        recyclerView2.adapter = InformationSlideAdapter(items2)

        binding.informationInputboxEt.setOnClickListener{
            val bottomSheet = InfoButtomSheetDialog()
            bottomSheet.show(parentFragmentManager, "InfoBottomSheetDialog")
        }


    }
}