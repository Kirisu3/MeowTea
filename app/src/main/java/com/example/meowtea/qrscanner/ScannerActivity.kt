package com.example.meowtea.qrscanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.meowtea.R
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class ScannerActivity : AppCompatActivity() {
    private lateinit var barcodeView: DecoratedBarcodeView
    private lateinit var captureManager: CaptureManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        barcodeView = findViewById(R.id.scanner_view)
        captureManager = CaptureManager(this, barcodeView)
        captureManager.initializeFromIntent(intent, savedInstanceState)
        captureManager.decode()

        val barcodeCallback = MyBarcodeCallback() // instance
        barcodeView.decodeSingle(barcodeCallback)
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        captureManager.onSaveInstanceState(outState)
    }
}

