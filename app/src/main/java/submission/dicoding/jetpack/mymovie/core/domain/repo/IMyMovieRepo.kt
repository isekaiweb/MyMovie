package submission.dicoding.jetpack.mymovie.core.domain.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import submission.dicoding.jetpack.mymovie.core.data.Resource
import submission.dicoding.jetpack.mymovie.core.domain.model.FavoriteData
import submission.dicoding.jetpack.mymovie.core.domain.model.AllData

interface IMyMovieRepo {
    fun getAllList(
        mediaType: String,
        category: String
    ): Flow<PagingData<AllData>>

    fun searchAllType(
        query: String
    ): Flow<PagingData<AllData>>

    suspend fun getDetailItem(mediaType: String, mediaId: Int): Resource<AllData>

    fun getAllFavorite(mediaType: String): Flow<PagingData<FavoriteData>>
    fun getSumOfAllFavorite(mediaType: String): LiveData<Int>
    fun isFavorite(id: Int): LiveData<Int>
    suspend fun insertFavorite(favoriteData: FavoriteData)
    suspend fun deleteFavorite(favoriteData: FavoriteData)

}