package com.example.waytogo.information

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.waytogo.R

class InformationSlideAdapter(private val items: List<InformationItem>) :
    RecyclerView.Adapter<InformationSlideAdapter.SliderViewHolder>() {

    class SliderViewHolder(view: View) :RecyclerView.ViewHolder(view){
        val imageview : ImageView = view.findViewById(R.id.informationSlideImage_iv)
        val title : TextView = view.findViewById(R.id.informationSlideTitle_tv)
        val location : TextView = view.findViewById(R.id.informationSlideLocation_tv)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_information_slider, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: InformationSlideAdapter.SliderViewHolder, position: Int) {
        val item = items[position]
        holder.imageview.setImageResource(item.image)
        holder.title.text = item.title
        holder.location.text = item.location
    }

    override fun getItemCount(): Int {
        return items.size
    }

}