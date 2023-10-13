package com.example.meowtea


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.meowtea.database.AppDatabase
import com.example.meowtea.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val storeFragment = StoreFragment()
    private val cartFragment = CartFragment()
    val coroutineScope = CoroutineScope(Dispatchers.IO)

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

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val milkTeaAdapter = MilkTeaAdapter(ArrayList()) // Initialize with an empty list

        val milkTeaDao = milkTeaDatabase.milkTeaDao()

        coroutineScope.launch(Dispatchers.IO) {
            // Fetch the milk teas from the database on a background thread
            val milkTeas = milkTeaDao.getAll()

            // Update the UI on the main thread with the result
            withContext(Dispatchers.Main) {
                milkTeaAdapter.updateData(milkTeas)
                recyclerView.adapter = milkTeaAdapter
            }
        }
    }





    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }


}
