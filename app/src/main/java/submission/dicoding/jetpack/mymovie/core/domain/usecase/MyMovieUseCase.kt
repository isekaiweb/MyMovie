package submission.dicoding.jetpack.mymovie.core.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import submission.dicoding.jetpack.mymovie.core.domain.model.AllData
import submission.dicoding.jetpack.mymovie.core.domain.model.FavoriteData
import submission.dicoding.jetpack.mymovie.core.util.Resource

interface MyMovieUseCase {

    fun getAllList(
        mediaType: String,
        category: String,
    ): Flow<PagingData<AllData>>

    fun searchAllType(
        query: String
    ): Flow<PagingData<AllData>>

    fun getDetailItem(mediaType: String, mediaId: Int): Flow<Resource<AllData>>

    fun getAllFavorite(mediaType: String): Flow<PagingData<FavoriteData>>
    fun getSumOfAllFavorite(mediaType: String): Flow<Int>
    fun isFavorite(id: Int): Flow<Int>
    suspend fun insertFavorite(favoriteData: FavoriteData)
    suspend fun deleteFavorite(favoriteData: FavoriteData)
}