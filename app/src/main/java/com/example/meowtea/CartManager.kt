package com.example.meowtea
object CartManager {
    private var pendingCartItem: CartItem? = null

    fun setPendingCartItem(cartItem: CartItem?) {
        pendingCartItem = cartItem
    }

    fun getPendingCartItem(): CartItem? {
        return pendingCartItem
    }
}
