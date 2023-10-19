package com.example.meowtea

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.example.meowtea.databinding.ActivityMainBinding
import com.example.meowtea.qrscanner.ScannerActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val storeFragment = StoreFragment()
    private val cartFragment = CartFragment()
    private val detailFragment = DetailFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(storeFragment)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.store -> {
                    replaceFragment(storeFragment)
                }

                R.id.cart -> {
                    replaceFragment(cartFragment)
                }
            }
            true
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.btScanner) {
            // Launch the com.example.meowtea.qrscanner.ScannerActivity when "btScanner" is clicked
            val intent = Intent(this, ScannerActivity::class.java)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }





    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }


}
