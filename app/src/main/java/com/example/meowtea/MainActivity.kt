package com.example.meowtea


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.meowtea.database.AppDatabase
import com.example.meowtea.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val storeFragment = StoreFragment()
    private val cartFragment = CartFragment()

    private val milkTeaDatabase: AppDatabase by lazy {
        AppDatabase.create(this)
    }

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

        // Get all of the milk teas in the database
        val milkTeas = milkTeaDatabase.milkTeaDao().getAll()

        // Create the milk tea adapter
        val milkTeaAdapter = MilkTeaAdapter(milkTeas)

        // Set the adapter for the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = milkTeaAdapter

    }



    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }


}
