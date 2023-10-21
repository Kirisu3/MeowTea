package com.example.meowtea.qrscanner

import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult

class MyBarcodeCallback : BarcodeCallback {
    override fun barcodeResult(result: BarcodeResult?) {
        if (result != null) {
            val resultText = result.text
            // Handle scanned QR code  here
        }
    }


    override fun possibleResultPoints(resultPoints: List<ResultPoint>?) {
        // Handle possible result points if needed
    }
}
