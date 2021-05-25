package submission.dicoding.jetpack.mymovie.core.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import submission.dicoding.jetpack.mymovie.core.domain.model.AllData
import submission.dicoding.jetpack.mymovie.core.domain.model.FavoriteData
import submission.dicoding.jetpack.mymovie.core.domain.repo.IMyMovieRepo
import submission.dicoding.jetpack.mymovie.core.util.Resource
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

    override  fun getDetailItem(mediaType: String, mediaId: Int): Flow<Resource<AllData>> =
        iRepo.getDetailItem(mediaType, mediaId)

    override fun getAllFavorite(): Flow<PagingData<FavoriteData>> =
        iRepo.getAllFavorite()

    override fun getSumOfAllFavorite(): Flow<Int> =
        iRepo.getSumOfAllFavorite()

    override fun isFavorite(id: Int): Flow<Int> = iRepo.isFavorite(id)

    override suspend fun insertFavorite(favoriteData: FavoriteData) =
        iRepo.insertFavorite(favoriteData)

    override suspend fun deleteFavorite(favoriteData: FavoriteData) =
        iRepo.deleteFavorite(favoriteData)
}