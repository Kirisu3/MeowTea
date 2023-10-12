package com.example.meowtea.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MilkTeaDao {
    @Insert
    suspend fun insert(milkTea: MilkTea)

    @Query("SELECT * FROM MilkTea")
    suspend fun getAllMilkTeas(): List<MilkTea>
}