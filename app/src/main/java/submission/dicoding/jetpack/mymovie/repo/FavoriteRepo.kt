package submission.dicoding.jetpack.mymovie.repo

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import submission.dicoding.jetpack.mymovie.db.FavoriteDao
import submission.dicoding.jetpack.mymovie.models.FavoriteEntity
import javax.inject.Inject

class FavoriteRepo @Inject constructor(
    private val dao: FavoriteDao
) : BaseFavoriteRepo {
    override suspend fun insert(favorite: FavoriteEntity) =
        dao.insert(favorite)

    override suspend fun delete(favorite: FavoriteEntity) = dao.delete(favorite)

    override suspend fun checkFavorite(id: Int): Int = dao.checkFavorite(id)

    override fun countFavorite(mediaType: String): LiveData<Int> = dao.countFavorite(mediaType)


    override fun getAllFavorite(mediaType: String): LiveData<PagingData<FavoriteEntity>> =
        Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { dao.getAllFavorite(mediaType) }
        ).liveData
}