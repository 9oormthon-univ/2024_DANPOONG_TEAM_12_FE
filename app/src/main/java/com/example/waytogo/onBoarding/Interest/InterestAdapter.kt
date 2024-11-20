package com.example.waytogo.onBoarding.Interest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.waytogo.R
import com.example.waytogo.databinding.InterestItemBinding

// RecyclerHorizontalAdapter.kt
class InterestAdapter(val items: MutableList<InterestData>) :
    RecyclerView.Adapter<InterestAdapter.ViewHolder>() {
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
        val view = ViewHolder(InterestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
            if (items[position].isSelected){
                items[position].isSelected = false
                notifyItemChanged(position)
            }
            else {
                items[position].isSelected = true
                notifyItemChanged(position)
            }
        }
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    inner class ViewHolder(val binding: InterestItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(items: InterestData) {
            val image = binding.interestItemIv
            val title = binding.contentTv

            image.setImageResource(items.image)
            title.text = items.content

            if (items.isSelected){
                binding.root.setBackgroundResource(R.drawable.interest_select_button)
                title.setTextColor(ContextCompat.getColor(binding.root.context, R.color.blue))
            }
            else {
                binding.root.setBackgroundResource(R.drawable.interest_button)
                title.setTextColor(ContextCompat.getColor(binding.root.context, R.color.gray300))
            }
        }
    }
}