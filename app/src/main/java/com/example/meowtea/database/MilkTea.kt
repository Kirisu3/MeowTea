package com.example.meowtea.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MilkTeaTable")
data class MilkTea(

    @PrimaryKey(autoGenerate = true) val id:  Int?,
    @ColumnInfo(name= "name") val name: String,
    @ColumnInfo(name= "price") val price: Double,
    @ColumnInfo(name= "imgPath") val image: String
)
