package submission.dicoding.jetpack.mymovie.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import submission.dicoding.jetpack.mymovie.core.domain.model.AllData
import submission.dicoding.jetpack.mymovie.core.domain.model.FavoriteData
import submission.dicoding.jetpack.mymovie.core.domain.usecase.MyMovieUseCase
import submission.dicoding.jetpack.mymovie.core.util.Resource
import submission.dicoding.jetpack.mymovie.util.FakeData
import submission.dicoding.jetpack.mymovie.util.FakePagingSourceAllData
import submission.dicoding.jetpack.mymovie.util.FakePagingSourceFavoriteData


class MyMovieInteractorTest : MyMovieUseCase {
    private fun allDataPaging(): Flow<PagingData<AllData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FakePagingSourceAllData() }
        ).flow
    }

    private fun favoriteDataPaging(data: MutableList<FavoriteData>): Flow<PagingData<FavoriteData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FakePagingSourceFavoriteData(data) }
        ).flow
    }


    override fun getAllList(mediaType: String, category: String): Flow<PagingData<AllData>> =
        allDataPaging()

    override fun searchAllType(query: String): Flow<PagingData<AllData>> = allDataPaging()

    override fun getDetailItem(mediaType: String, mediaId: Int): Flow<Resource<AllData>> = flow {
        if (FakeData.createAllData.id == mediaId)
            emit(Resource.success(FakeData.createAllData))
        else emit(Resource.error<AllData>("data not found"))
    }

    private val listFavorite = mutableListOf<FavoriteData>()

    override fun getAllFavorite(): Flow<PagingData<FavoriteData>> = favoriteDataPaging(
        listFavorite
    )

    override fun getSumOfAllFavorite(): Flow<Int> = flow {
        favoriteDataPaging(listFavorite).count()
    }

    override fun isFavorite(id: Int): Flow<Int> = flow {
        emit(listFavorite.filter {
            it.id == id
        }.count())
    }

    override suspend fun insertFavorite(favoriteData: FavoriteData) {
        listFavorite.add(favoriteData)
    }


    override suspend fun deleteFavorite(favoriteData: FavoriteData) {
        listFavorite.remove(favoriteData)
    }


}