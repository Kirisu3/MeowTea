package com.example.meowtea.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MilkTeaTable")
data class MilkTea(

    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val price: Int,
    val imagePath: String
)