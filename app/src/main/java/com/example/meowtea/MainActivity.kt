package com.example.meowtea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.meowtea.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivity.inflate(layoutInflater) // Corrected the binding initialization
        setContentView(binding.root)
        replaceFragment(this, StoreFragment)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.store -> {
                    // Handle home item
                }
                R.id.cart -> {
                    // Handle profile item
                }

            }
            true
        }


    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}