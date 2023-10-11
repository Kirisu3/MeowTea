package com.example.meowtea.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MilkTea(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val price: Double,
    val image: String
)
