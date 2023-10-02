import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream

class MilkTeaDatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_NAME = "MilkTea.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "milktea"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PRICE = "price"
        const val COLUMN_IMAGE_PATH = "imagePath"
    }

    init {

        if (!checkDatabase()) {

            copyDatabase()
        }
    }

    private fun checkDatabase(): Boolean {
        val dbFile = context.getDatabasePath(DATABASE_NAME)
        return dbFile.exists()
    }

    private fun copyDatabase() {
        val inputStream = context.assets.open(DATABASE_NAME)
        val outputPath = context.getDatabasePath(DATABASE_NAME).absolutePath

        val outputStream = FileOutputStream(outputPath)
        val buffer = ByteArray(1024)
        var length: Int

        while (inputStream.read(buffer).also { length = it } > 0) {
            outputStream.write(buffer, 0, length)
        }

        outputStream.flush()
        outputStream.close()
        inputStream.close()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // NOTE insert initial data here if death aa
        // db?.execSQL("INSERT INTO MilkTea (id,name, price, imagePath) VALUES ('Green Tea', 'Refreshing tea', 3.99, 'tea_image.jpg')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {

        }

        // TODO make test populate milktea.db > put into assets (android studio) > continue ugh
    }
}