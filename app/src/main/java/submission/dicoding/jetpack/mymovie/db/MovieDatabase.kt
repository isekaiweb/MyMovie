package submission.dicoding.jetpack.mymovie.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import submission.dicoding.jetpack.mymovie.models.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
