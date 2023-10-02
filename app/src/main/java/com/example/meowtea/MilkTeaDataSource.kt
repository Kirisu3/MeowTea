package com.example.meowtea

interface MilkTeaDataSource {
    fun getAllMilkTeas(): List<MilkTea>
    fun getMilkTeaById(id: Int): MilkTea?
    fun addMilkTea(milkTea: MilkTea)
    fun updateMilkTea(milkTea: MilkTea)
    fun deleteMilkTea(id: Int)
}