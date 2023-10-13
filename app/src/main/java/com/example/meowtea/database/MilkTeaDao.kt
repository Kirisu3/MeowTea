package com.example.meowtea.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MilkTeaDao {
    @Query("SELECT * FROM MilkTeaTable")
    fun getAll(): List<MilkTea>

    @Query("SELECT * FROM MilkTeaTable WHERE id = :id")
    fun get(id: Int): MilkTea

    @Insert
    fun insert(milktea: MilkTea)

    @Update
    fun update(milktea: MilkTea)

    @Delete
    fun delete(milktea: MilkTea)
}