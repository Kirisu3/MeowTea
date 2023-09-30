package com.example.meowtea

import java.util.*
data class ProductName(
    var id: Int = getAutoId(),
    var COLUMN_NAME: String = "",
    var COLUMN_PRICE: String = ""
){
    companion object{
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }

}

