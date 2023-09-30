import android.provider.BaseColumns

object MilkTeaContract {
    // Define table and column names
    object MilkTeaEntry : BaseColumns {
        const val TABLE_NAME = "milk_tea"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_PRICE = "price"
        const val COLUMN_NAME_IMAGE = "image"
    }

    // SQL commands for creating and deleting the table
    const val SQL_CREATE_ENTRIES = """
    CREATE TABLE ${MilkTeaEntry.TABLE_NAME} (
        ${BaseColumns._ID} INTEGER PRIMARY KEY,
        ${MilkTeaEntry.COLUMN_NAME_NAME} TEXT,
        ${MilkTeaEntry.COLUMN_NAME_DESCRIPTION} TEXT,
        ${MilkTeaEntry.COLUMN_NAME_PRICE} REAL,
        ${MilkTeaEntry.COLUMN_NAME_IMAGE} TEXT
    )
    """


    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${MilkTeaEntry.TABLE_NAME}"
}
