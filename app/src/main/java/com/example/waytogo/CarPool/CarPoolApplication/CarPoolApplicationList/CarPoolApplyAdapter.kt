package com.example.waytogo.CarPool.CarPoolApplication.CarPoolApplicationList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.waytogo.CarPool.CarPoolApplication.CarPoolViewModel
import com.example.waytogo.CarPool.CarPoolModal.CarpoolApplicationModal
import com.example.waytogo.CarPool.CarPoolModal.CarpoolCancelModal
import com.example.waytogo.databinding.CarpoolApplyLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CarPoolApplyAdapter(val items: MutableList<ApplyList>, val fragmentManager: FragmentManager, val viewModel: CarPoolViewModel)
    : RecyclerView.Adapter<CarPoolApplyAdapter.ViewHolder>() {

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    private lateinit var itemClickListener: onItemClickListener

    fun setItemClickListener(itemClickListener: onItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = ViewHolder(CarpoolApplyLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
//            if (items[position].isSelected){
//                items[position].isSelected = false
//                notifyItemChanged(position)
//            }
//            else {
//                items[position].isSelected = true
//                notifyItemChanged(position)
//            }
        }
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    inner class ViewHolder(val binding: CarpoolApplyLayoutBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(items: ApplyList) {
            val image = binding.listImageIv
            val title = binding.titleTv
            val department = binding.departmentTv
            val destination = binding.destinationTv
            val date = binding.dateTv
            val time = binding.timeTv

            image.setImageResource(items.image)
            title.text = items.title
            department.text = items.department
            destination.text = items.destination
            date.text = items.date
            time.text = items.time

            binding.cancelButton.setOnClickListener {
                applyModal(CarpoolCancelModal())
            }
            viewModel.applyTitle.value = items.title
        }
    }

    // 모달 창 열기 함수
    private fun applyModal(modal: BottomSheetDialogFragment) {
        val carpoolModal = modal
        carpoolModal.show(fragmentManager, CarpoolApplicationModal.TAG)
    }
}