package com.example.meowtea

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class sqlitedatabase(context:Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "meowtea.db"
        private const val TABLE_PRODUCTS = "products"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_PRICE = "price"

    }
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTblProducts = ("CREATE TABLE " + TABLE_PRODUCTS + "("
                + COLUMN_ID + "INTEGER PRIMARY KEY," + COLUMN_NAME + "TEXT,"
                + COLUMN_PRICE + "TEXT" + ")")
        readableDatabase?.execSQL(createTblProducts)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        readableDatabase!!.execSQL("DROP TABLE IF $TABLE_PRODUCTS")
        onCreate(readableDatabase)
    }

    fun insertProduct(ProductName): Long {
        val readableDatabase = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COLUMN_ID)
        contentValues.put(COLUMN_NAME)
        contentValues.put(COLUMN_PRICE)

        val success = readableDatabase.insert(TABLE_PRODUCTS, null, ContentValues)
        readableDatabase.close()
        return success
    }
    fun getALLProducts(): ArrayList<ProductName>{
        val ProductList = ArrayList<ProductName> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_PRODUCTS"
        val readableDatabase = this.readableDatabase

        val cursor: Cursor?
    }
}