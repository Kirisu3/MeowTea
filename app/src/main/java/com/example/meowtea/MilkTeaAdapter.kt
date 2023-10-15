package com.example.meowtea

import com.example.meowtea.database.MilkTea
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MilkTeaAdapter(private var milkTeas: List<MilkTea>, private val onItemClick: (MilkTea) -> Unit) : RecyclerView.Adapter<MilkTeaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.tv_title)
        val imageView: ImageView = itemView.findViewById(R.id.ivProduct)
    }

    fun updateData(newMilkTeas: List<MilkTea>) {
        milkTeas = newMilkTeas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.milk_tea_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val milkTea = milkTeas[position]
        holder.textViewName.text = milkTea.name

        // Load the image from the database using Glide
        Glide.with(holder.imageView.context)
            .load(milkTea.imagePath)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            onItemClick(milkTea)
        }
    }

    override fun getItemCount(): Int {
        return milkTeas.size
    }
}

