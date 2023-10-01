import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream

class MilkTeaDatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "MilkTea.db"
        private const val DATABASE_VERSION = 1
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
        db?.execSQL("""
        CREATE TABLE IF NOT EXISTS MilkTea (
            _id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT,
            description TEXT,
            price REAL,
            imagePath TEXT
        )
    """.trimIndent())

        // TODO NOTE insert initial data here if fallout
        // Example:
        // db?.execSQL("INSERT INTO MilkTea (id,name, description, price, imagePath) VALUES ('Green Tea', 'Refreshing tea', 3.99, 'tea_image.jpg')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            // Upgrade from version 1 to version 2
            db?.execSQL("ALTER TABLE MilkTea ADD COLUMN new_column TEXT")
        }

        // Handle other upgrade scenarios as needed
    }
}