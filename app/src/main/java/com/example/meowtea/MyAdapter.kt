package com.example.meowtea

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter (private val myList: ArrayList<ProductData>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val imgList: ImageView = itemView.findViewById(R.id.ivProduct)
        val productname: TextView = itemView.findViewById(R.id.tvNameofMilkTea)
        val productdescription: TextView = itemView.findViewById(R.id.tvDescription)
        val productprice: TextView = itemView.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.milkteaitem, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = myList[position]
        holder.imgList.setImageResource(currentItem.img)
        holder.productname.text = currentItem.name
        holder.productdescription.text = currentItem.description
        holder.productprice.text= currentItem.price
    }
    override fun getItemCount(): Int {
        return myList.size
    }


}