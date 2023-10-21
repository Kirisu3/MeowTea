package com.example.meowtea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartFragment : Fragment() {

    private val cartItems = mutableListOf<CartItem>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var totalPriceTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        totalPriceTextView = view.findViewById(R.id.totalPriceTextView)
        updateTotalPrice()

        recyclerView = view.findViewById(R.id.cartRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        cartAdapter = CartAdapter(cartItems) { position ->
            removeItem(position)
        }

        recyclerView.adapter = cartAdapter




        return view


    }

    fun addItemToCart(item: CartItem) {
        cartItems.add(item)

        // Make sure the cartAdapter is not null before using it
        if (::cartAdapter.isInitialized) {
            cartAdapter.notifyItemInserted(cartItems.size - 1)
        }
        updateTotalPrice()
    }

    private fun updateTotalPrice() {
        var total = 0
        for (item in cartItems) {
            total += item.itemPrice
        }

        totalPriceTextView.text = "Total: ₱$total"
    }
    fun removeItem(position: Int) {
        if (position >= 0 && position < cartItems.size) {
            cartItems.removeAt(position)
            cartAdapter.notifyItemRemoved(position)
            updateTotalPrice()
        }
    }

}
