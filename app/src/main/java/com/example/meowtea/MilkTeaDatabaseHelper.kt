import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MilkTeaDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Create the database schema here
        db.execSQL(MilkTeaContract.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database upgrades (e.g., altering tables)
        db.execSQL(MilkTeaContract.SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MilkTea.db"
    }

    // Create or open the database
    val dbHelper = MilkTeaDatabaseHelper(context)
    val db = dbHelper.writableDatabase

    // Define values to insert
    val values = ContentValues().apply {
        put(MilkTeaContract.MilkTeaEntry.COLUMN_NAME_NAME, "Green Tea")
        put(MilkTeaContract.MilkTeaEntry.COLUMN_NAME_DESCRIPTION, "Refreshing green tea with milk and booba")
        put(MilkTeaContract.MilkTeaEntry.COLUMN_NAME_PRICE, 39.99)
        put(MilkTeaContract.MilkTeaEntry.COLUMN_NAME_IMAGE, "path_to_image") //image-vector
    }

    // Insert the row
    val newRowId = db?.insert(MilkTeaContract.MilkTeaEntry.TABLE_NAME, null, values)

    fun queryMilkTeaItems(){
        val projection = arrayOf(
            MilkTeaContract.MilkTeaEntry.COLUMN_NAME_NAME,
            MilkTeaContract.MilkTeaEntry.COLUMN_NAME_DESCRIPTION,
            MilkTeaContract.MilkTeaEntry.COLUMN_NAME_PRICE,
            MilkTeaContract.MilkTeaEntry.COLUMN_NAME_IMAGE // Include the image column
        )

        val cursor = db?.query(
            MilkTeaContract.MilkTeaEntry.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            null
        )

        cursor?.use {
            while (it.moveToNext()) {
                val name = it.getString(it.getColumnIndexOrThrow(MilkTeaContract.MilkTeaEntry.COLUMN_NAME_NAME))
                val description = it.getString(it.getColumnIndexOrThrow(MilkTeaContract.MilkTeaEntry.COLUMN_NAME_DESCRIPTION))
                val price = it.getDouble(it.getColumnIndexOrThrow(MilkTeaContract.MilkTeaEntry.COLUMN_NAME_PRICE))
                val imagePath = it.getString(it.getColumnIndexOrThrow(MilkTeaContract.MilkTeaEntry.COLUMN_NAME_IMAGE))

                // Load and display the image using the 'imagePath'
                // Process other retrieved data
            }
        }
    }


}


