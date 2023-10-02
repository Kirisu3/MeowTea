import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import com.example.meowtea.MilkTea
import com.example.meowtea.MilkTeaDataSource

class LocalMilkTeaDataSource(context: Context) : MilkTeaDataSource {

    private val dbHelper = MilkTeaDatabaseHelper(context)
    private val database = dbHelper.writableDatabase

    @SuppressLint("Range")
    override fun getAllMilkTeas(): List<MilkTea> {
        val milkTeas = mutableListOf<MilkTea>()

        val query = "SELECT * FROM ${MilkTeaDatabaseHelper.TABLE_NAME}"
        val cursor = database.rawQuery(query, null)

        cursor.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndex(MilkTeaDatabaseHelper.COLUMN_ID))
                val name = it.getString(it.getColumnIndex(MilkTeaDatabaseHelper.COLUMN_NAME))
                val price = it.getDouble(it.getColumnIndex(MilkTeaDatabaseHelper.COLUMN_PRICE))
                val imagePath = it.getString(it.getColumnIndex(MilkTeaDatabaseHelper.COLUMN_IMAGE_PATH))

                val milkTea = MilkTea(id, name, price, imagePath)
                milkTeas.add(milkTea)
            }
        }

        return milkTeas
    }

    @SuppressLint("Range")
    override fun getMilkTeaById(id: Int): MilkTea? {
        val query = "SELECT * FROM ${MilkTeaDatabaseHelper.TABLE_NAME} WHERE ${MilkTeaDatabaseHelper.COLUMN_ID} = ?"
        val cursor = database.rawQuery(query, arrayOf(id.toString()))

        if (cursor.moveToFirst()) {
            val name = cursor.getString(cursor.getColumnIndex(MilkTeaDatabaseHelper.COLUMN_NAME))
            val price = cursor.getDouble(cursor.getColumnIndex(MilkTeaDatabaseHelper.COLUMN_PRICE))
            val imagePath = cursor.getString(cursor.getColumnIndex(MilkTeaDatabaseHelper.COLUMN_IMAGE_PATH))

            return MilkTea(id, name, price, imagePath)
        }


        return null
    }


    override fun addMilkTea(milkTea: MilkTea) {
        val values = ContentValues().apply {
            put(MilkTeaDatabaseHelper.COLUMN_NAME, milkTea.name)
            put(MilkTeaDatabaseHelper.COLUMN_PRICE, milkTea.price)
            put(MilkTeaDatabaseHelper.COLUMN_IMAGE_PATH, milkTea.imagePath)
        }

        database.insert(MilkTeaDatabaseHelper.TABLE_NAME, null, values)
    }

    override fun updateMilkTea(milkTea: MilkTea) {
        val values = ContentValues().apply {
            put(MilkTeaDatabaseHelper.COLUMN_NAME, milkTea.name)
            put(MilkTeaDatabaseHelper.COLUMN_PRICE, milkTea.price)
            put(MilkTeaDatabaseHelper.COLUMN_IMAGE_PATH, milkTea.imagePath)
        }

        val whereClause = "${MilkTeaDatabaseHelper.COLUMN_ID} = ?"
        val whereArgs = arrayOf(milkTea.id.toString())

        database.update(MilkTeaDatabaseHelper.TABLE_NAME, values, whereClause, whereArgs)
    }

    override fun deleteMilkTea(id: Int) {
        val whereClause = "${MilkTeaDatabaseHelper.COLUMN_ID} = ?"
        val whereArgs = arrayOf(id.toString())

        database.delete(MilkTeaDatabaseHelper.TABLE_NAME, whereClause, whereArgs)
    }
}
