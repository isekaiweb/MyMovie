package submission.dicoding.jetpack.mymovie.core.data.source.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import submission.dicoding.jetpack.mymovie.core.data.source.local.db.FavoriteDao
import submission.dicoding.jetpack.mymovie.core.data.source.local.entity.FavoriteEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val dao: FavoriteDao) {
    fun getAllFavorite(mediaType: String): Flow<PagingData<FavoriteEntity>> =
        Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { dao.getAllFavorite(mediaType) }
        ).flow

    suspend fun insertFavorite(favoriteEntity: FavoriteEntity) =
        dao.insertFavorite(favoriteEntity)

    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) =
        dao.deleteFavorite(favoriteEntity)

    fun isFavorite(id: Int): Flow<Int> = dao.isFavorite(id)

    fun getSumOfAllFavorite(mediaType: String): Flow<Int> =
        dao.getSumOfAllFavorite(mediaType)
}