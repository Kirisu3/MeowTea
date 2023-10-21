package com.example.meowtea

// CartManager.kt
object CartManager {
    private var pendingCartItem: CartItem? = null

    fun setPendingCartItem(cartItem: CartItem?) {
        pendingCartItem = cartItem
    }

    fun getPendingCartItem(): CartItem? {
        return pendingCartItem
    }
}
