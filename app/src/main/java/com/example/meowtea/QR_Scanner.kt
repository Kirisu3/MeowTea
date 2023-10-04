package com.example.meowtea

import android.Manifest
import android.content.pm.PackageManager
import android.hardware.Camera.ErrorCallback
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.qrcode.encoder.QRCode
import com.journeyapps.barcodescanner.BarcodeView
import com.journeyapps.barcodescanner.DecoderResultPointCallback
import com.journeyapps.barcodescanner.camera.AutoFocusManager

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
        codescanner.formats = BarcodeView.ALL_FORMATS

        codescanner.autoFocusMode = AutoFocusMode.SAFE
        codescanner.scanMode = ScanMode.SINGLE
        codescanner.isAutoFocusEnabled = true
        codescanner.isFlashEnabled = false

        codescanner.decodeCaLLback = DecodeCaLLback {
            runOnUiThread {
                Toast.makeText(this, "Scan Result: ${it.text}", Toast.LENGTH_SHORT).show()
            }
        }

        codescanner.errorCaLLback = ErrorCallback {
            runOnUiThread {
                Toast.makeText(this, "camera initialization error: ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }

        scannerView.setOnClickListener {
            codescanner.startScanning()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show()
                startScanning()
            }else{
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::codescanner.isInitialized){
            codescanner?.startScanning()
        }
    }

    override fun onPause() {
        if (::codescanner.isInitialized){
            codescanner?.releaseResources()
        }
        super.onPause()
    }