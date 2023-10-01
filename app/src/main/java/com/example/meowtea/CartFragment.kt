package com.example.meowtea

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

class CartFragment : Fragment() {

    private lateinit var ivQRcode: ImageView
    private lateinit var etData: EditText
    private lateinit var btnGenerateQRcode: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        ivQRcode = view.findViewById(R.id.ivQRCode)
        etData = view.findViewById(R.id.etData)
        btnGenerateQRcode = view.findViewById(R.id.btnGenerateQRcode)

        btnGenerateQRcode.setOnClickListener {
            val data = etData.text.toString().trim()

            if (data.isEmpty()) {
                Toast.makeText(requireContext(), "Enter some data", Toast.LENGTH_SHORT).show()
            } else {
                val writer = QRCodeWriter()
                try {
                    val bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512)
                    val width = bitMatrix.width
                    val height = bitMatrix.height
                    val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
                    for (x in 0 until width) {
                        for (y in 0 until height) {
                            bmp.setPixel(
                                x, y,
                                if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
                            )
                        }
                    }
                    ivQRcode.setImageBitmap(bmp)
                } catch (e: WriterException) {
                    e.printStackTrace()
                }
            }
        }

        return view
    }
}
