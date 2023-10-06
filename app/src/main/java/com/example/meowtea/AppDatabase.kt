import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MilkTea::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun milkTeaDao(): MilkTeaDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "milktea-database"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}
