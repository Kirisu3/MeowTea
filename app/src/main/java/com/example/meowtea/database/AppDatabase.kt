package com.example.meowtea.database

import com.example.meowtea.database.MilkTea
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MilkTea::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun milkTeaDao(): MilkTeaDao

    companion object {
        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "milktea.db")
                .createFromAsset("databases/MilkTea.db").build()
        }
    }
}