package submission.dicoding.jetpack.mymovie.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import submission.dicoding.jetpack.mymovie.core.data.Resource
import submission.dicoding.jetpack.mymovie.core.domain.model.AllData
import submission.dicoding.jetpack.mymovie.core.domain.model.FavoriteData
import submission.dicoding.jetpack.mymovie.core.domain.repo.IMyMovieRepo
import javax.inject.Inject

class MyMovieInteractor @Inject constructor(
    private val iRepo: IMyMovieRepo
) : MyMovieUseCase {
    override fun getAllList(
        mediaType: String,
        category: String,
    ): Flow<PagingData<AllData>> = iRepo.getAllList(mediaType, category)

    override fun searchAllType(
        query: String
    ): Flow<PagingData<AllData>> = iRepo.searchAllType(query)
    override suspend fun getDetailItem(mediaType: String, mediaId: Int): Resource<AllData> =
        iRepo.getDetailItem(mediaType, mediaId)

    override fun getAllFavorite(mediaType: String): Flow<PagingData<FavoriteData>> =
        iRepo.getAllFavorite(mediaType)

    override fun getSumOfAllFavorite(mediaType: String): LiveData<Int> =
        iRepo.getSumOfAllFavorite(mediaType)

    override fun isFavorite(id: Int): LiveData<Int> = iRepo.isFavorite(id)

    override suspend fun insertFavorite(favoriteData: FavoriteData) =
        iRepo.insertFavorite(favoriteData)

    override suspend fun deleteFavorite(favoriteData: FavoriteData) =
        iRepo.deleteFavorite(favoriteData)
}