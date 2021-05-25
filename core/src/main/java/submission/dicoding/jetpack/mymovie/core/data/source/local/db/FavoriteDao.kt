package submission.dicoding.jetpack.mymovie.core.data.source.local.db

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import submission.dicoding.jetpack.mymovie.core.data.source.local.entity.FavoriteEntity

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteEntity)

    @Query("SELECT count(*) FROM favorite WHERE id = :id")
    fun isFavorite(id: Int): Flow<Int>

    @Query("SELECT count(*) FROM favorite")
    fun getSumOfAllFavorite(): Flow<Int>

    @Query("SELECT * FROM favorite")
    fun getAllFavorite(): PagingSource<Int, FavoriteEntity>
}