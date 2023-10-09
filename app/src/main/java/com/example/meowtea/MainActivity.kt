package com.example.meowtea

import StoreFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.meowtea.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val storeFragment = StoreFragment()
    private val cartFragment = CartFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        //val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        //val adapter = MilkTeaAdapter(this, yourMilkTeaDataList) // Replace with your data
        //recyclerView.adapter = adapter
        //recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }


}
