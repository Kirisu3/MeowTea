package com.example.meowtea

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder

class CartFragment : Fragment() {

    private val cartItems = mutableListOf<CartItem>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private lateinit var totalPriceTextView: TextView
    private lateinit var btnGenerateQR: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)


        totalPriceTextView = view.findViewById(R.id.totalPriceTextView)
        updateTotalPrice()

        recyclerView = view.findViewById(R.id.cartRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val pendingCartItem = CartManager.getPendingCartItem()
        if (pendingCartItem != null) {
            addItemToCart(pendingCartItem)
            CartManager.setPendingCartItem(null) // Clear the pending item
        }


        cartAdapter = CartAdapter(cartItems) { position ->
            removeItem(position)
        }

        recyclerView.adapter = cartAdapter

        btnGenerateQR = view.findViewById(R.id.btnGenerateQR)
        btnGenerateQR.setOnClickListener {
            val qrCodeBitmap = generateQRCodeFromCartItems()
            if (qrCodeBitmap != null) {
                openQRCodeDialog(qrCodeBitmap)
            }
        }

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

    private fun generateQRCodeFromCartItems(): Bitmap? {
        val cartInfo = buildCartInfoString()


        return generateQRCode(cartInfo)
    }

    private fun buildCartInfoString(): String {
        val cartInfoBuilder = StringBuilder()
        for (item in cartItems) {
            cartInfoBuilder.append("${item.itemName} - ₱${item.itemPrice}\n")
        }
        return cartInfoBuilder.toString()
    }

    private fun generateQRCode(data: String): Bitmap? {
        val multiFormatWriter = MultiFormatWriter()
        try {
            val bitMatrix = multiFormatWriter.encode(data, BarcodeFormat.QR_CODE, 500, 500)
            val barcodeEncoder = BarcodeEncoder()
            return barcodeEncoder.createBitmap(bitMatrix)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    private fun openQRCodeDialog(qrCodeBitmap: Bitmap) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.qr_code_dialog, null)
        val imageViewQRCode = dialogView.findViewById<ImageView>(R.id.imageViewQRCode)
        imageViewQRCode.setImageBitmap(qrCodeBitmap)

        val builder = android.app.AlertDialog.Builder(context)
        builder.setView(dialogView)
        builder.setCancelable(true)

        val dialog = builder.create()
        dialog.show()
    }

}
