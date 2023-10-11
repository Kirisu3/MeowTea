package com.example.meowtea.database

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meowtea.R

class MilkTeaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.imageView)
    val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    //val descriptionTextView: TextView = itemView.findViewById(R.id.tvDescription)
    val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
}
