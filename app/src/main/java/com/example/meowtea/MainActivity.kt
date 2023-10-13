package com.example.meowtea

import StoreFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.meowtea.database.AppDatabase
import com.example.meowtea.database.MilkTea
import com.example.meowtea.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val storeFragment = StoreFragment()
    private val cartFragment = CartFragment()

    private lateinit var appDb : AppDatabase

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



        val milktea1 = MilkTea(1, "Winter melon", 49.99, "res/drawable/noun_bubble_milk_tea_2216050.xml")
        val milktea2 = MilkTea(2, "Chocolate", 49.99, "res/drawable/noun_bubble_milk_tea_2216050.xml")
        val milktea3 = MilkTea(3, "Dark Choco", 49.99, "res/drawable/noun_bubble_milk_tea_2216050.xml")
        val milktea4 = MilkTea(4, "Okinawa", 49.99, "res/drawable/noun_bubble_milk_tea_2216050.xml")

        GlobalScope.launch(Dispatchers.IO){
            appDb.milkTeaDao().insert(milktea1)
            appDb.milkTeaDao().insert(milktea2)
            appDb.milkTeaDao().insert(milktea3)
            appDb.milkTeaDao().insert(milktea4)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }


}
