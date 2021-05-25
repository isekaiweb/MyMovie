package submission.dicoding.jetpack.mymovie.core.data.source.local.db

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import submission.dicoding.jetpack.mymovie.core.data.source.local.entity.FavoriteEntity

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: submission.dicoding.jetpack.mymovie.core.data.source.local.entity.FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favorite: submission.dicoding.jetpack.mymovie.core.data.source.local.entity.FavoriteEntity)

    @Query("SELECT count(*) FROM favorite WHERE id = :id")
    fun isFavorite(id: Int): Flow<Int>

    @Query("SELECT count(*) FROM favorite WHERE media_type = :mediaType")
    fun getSumOfAllFavorite(mediaType: String): Flow<Int>

    @Query("SELECT * FROM favorite  WHERE media_type = :mediaType")
    fun getAllFavorite(mediaType: String): PagingSource<Int, submission.dicoding.jetpack.mymovie.core.data.source.local.entity.FavoriteEntity>
}