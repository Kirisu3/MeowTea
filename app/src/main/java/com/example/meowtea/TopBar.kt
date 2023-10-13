package com.example.meowtea

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar


class TopBar : AppCompatActivity() {

    lateinit var topAppBar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topAppBar = findViewById(R.id.topAppBar)

        topAppBar.setNavigationOnClickListener {
            Toast.makeText(this, "Navigation IconClicked", Toast.LENGTH_SHORT).show()
        }
        topAppBar.setOnMenuItemClickListener { MenuItem ->
            when (MenuItem.itemId) {
                R.id.topAppBar -> {
                    Toast.makeText(this, "Navigation Icon Clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> {
                    false

                }
            }
        }
    }
}

