package submission.dicoding.jetpack.mymovie.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import submission.dicoding.jetpack.mymovie.models.FavoriteEntity
import submission.dicoding.jetpack.mymovie.utils.FakePagingSource

class FakeFavoriteRepo : BaseFavoriteRepo {
    private val favoriteItems = mutableListOf<FavoriteEntity>()


    override suspend fun insert(favorite: FavoriteEntity) {
        favoriteItems.add(favorite)
    }

    override suspend fun delete(favorite: FavoriteEntity) {
        favoriteItems.remove(favorite)
    }

    override suspend fun checkFavorite(id: Int): Int =
        favoriteItems.count {
            it.id == id
        }


    override fun countFavorite(mediaType: String): LiveData<Int> =
        MutableLiveData(favoriteItems.size)


    override fun getAllFavorite(mediaType: String): LiveData<PagingData<FavoriteEntity>> = Pager(
        config = PagingConfig(pageSize = 1, enablePlaceholders = false),
        pagingSourceFactory = { FakePagingSource() }
    ).liveData
}