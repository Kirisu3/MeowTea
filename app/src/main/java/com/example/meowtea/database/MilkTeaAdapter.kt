package com.example.meowtea.database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meowtea.R

class MilkTeaAdapter(private val milkTeaList: List<MilkTea>) : RecyclerView.Adapter<MilkTeaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MilkTeaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.milkteaitem, parent, false)
        return MilkTeaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MilkTeaViewHolder, position: Int) {
        val milkTea = milkTeaList[position]
        holder.nameTextView.text = milkTea.name
        // try Picasso if fail
        Glide.with(holder.imageView.context).load(milkTea.image).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return milkTeaList.size
    }
}
