package com.example.meowtea

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val cartItems: List<CartItem>, private val removeItemListener: (Int) -> Unit) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTextView: TextView = itemView.findViewById(R.id.tv_title)
        val itemImageView: ImageView = itemView.findViewById(R.id.ivProduct)
        val itemPriceTextView: TextView = itemView.findViewById(R.id.tv_price)
        val btRemove : Button = itemView.findViewById(R.id.removeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cart_tea_item, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = cartItems[position]

        holder.itemNameTextView.text = currentItem.itemName
        holder.itemImageView.setImageResource(currentItem.itemImageResId)
        holder.itemPriceTextView.text = currentItem.itemPrice.toString() + "₱"

        holder.btRemove.setOnClickListener {
            removeItemListener(position)
        }


    }

    override fun getItemCount(): Int {
        return cartItems.size
    }


}