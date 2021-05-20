package submission.dicoding.jetpack.mymovie.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import submission.dicoding.jetpack.mymovie.models.FavoriteEntity
import submission.dicoding.jetpack.mymovie.util.SingleEvent

interface BaseFavoriteRepo {
    suspend fun insert(favorite: FavoriteEntity)
    suspend fun delete(favorite: FavoriteEntity)
    suspend fun checkFavorite(id: Int): Int
    fun countFavorite(mediaType: String): LiveData<Int>
    fun getAllFavorite(mediaType: String): LiveData<PagingData<FavoriteEntity>>
}