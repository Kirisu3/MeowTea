package com.example.meowtea.qrscanner

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

import com.example.meowtea.R
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult

class MyBarcodeCallback (private val context: Context): BarcodeCallback {
    override fun barcodeResult(result: BarcodeResult?) {
        if (result != null) {
            val resultText = result.text
            showCustomDialog(resultText)
        }
    }


    override fun possibleResultPoints(resultPoints: List<ResultPoint>?) {

    }

    private fun showCustomDialog(resultText: String) {
        val dialogView = View.inflate(context, R.layout.custom_dialog, null)
        val textViewResult = dialogView.findViewById<TextView>(R.id.textViewResult)
        val buttonOK = dialogView.findViewById<Button>(R.id.buttonOK)

        textViewResult.text = resultText
        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        buttonOK.setOnClickListener {
            dialog.dismiss()

        }

        dialog.show()
    }
}
