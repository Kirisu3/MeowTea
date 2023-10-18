package com.example.meowtea.database

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meowtea.R

class MilkTeaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewName: TextView = itemView.findViewById(R.id.tv_title)
    val imageView: ImageView = itemView.findViewById(R.id.ivProduct)
}
