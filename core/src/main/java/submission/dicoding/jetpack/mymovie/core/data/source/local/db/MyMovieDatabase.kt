package submission.dicoding.jetpack.mymovie.core.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import submission.dicoding.jetpack.mymovie.core.data.source.local.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MyMovieDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
