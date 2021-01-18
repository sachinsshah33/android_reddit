package extension.domain.androidreddit.data.local


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import extension.domain.androidreddit.data.models.ApiResponseDataChildren
import extension.domain.androidreddit.data.models.ApiResponseDataChildrenData
import extension.domain.androidreddit.data.repo.dataDAO

@Database(
    entities = [ApiResponseDataChildrenData::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDAO(): dataDAO

    companion object {
        @Volatile var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app-db-001")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}