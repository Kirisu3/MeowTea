package com.example.meowtea

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.qrcode.encoder.QRCode
import com.journeyapps.barcodescanner.BarcodeView

class QR_Scanner  : AppCompatActivity() {

    private  lateinit var  codescanner: QR_Scanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
               PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 123)
        }else{
            startScanning()
        }
    }

    private fun startScanning() {
        val scannerView: BarcodeView = findViewById(R.id.zxing_barcode_scanner)
        codescanner = QR_Scanner(this, scannerView)
        codescanner.camera = BarcodeView.CAMERA_BACK
    }
}