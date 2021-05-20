package submission.dicoding.jetpack.mymovie.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import submission.dicoding.jetpack.mymovie.models.FavoriteEntity

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteEntity)

    @Delete
    suspend fun delete(favorite: FavoriteEntity)

    @Query("SELECT count(*) FROM table_favorite WHERE id = :id")
    suspend fun checkFavorite(id: Int): Int

    @Query("SELECT count(*) FROM table_favorite WHERE media_type = :mediaType")
    fun countFavorite(mediaType: String): LiveData<Int>

    @Query("SELECT * FROM table_favorite  WHERE media_type = :mediaType")
    fun getAllFavorite(mediaType: String): PagingSource<Int, FavoriteEntity>
}