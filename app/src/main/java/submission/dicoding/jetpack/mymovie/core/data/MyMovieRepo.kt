package submission.dicoding.jetpack.mymovie.core.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import submission.dicoding.jetpack.mymovie.core.data.source.local.LocalDataSource
import submission.dicoding.jetpack.mymovie.core.data.source.remote.RemoteDataSource
import submission.dicoding.jetpack.mymovie.core.data.source.remote.network.ApiResponse
import submission.dicoding.jetpack.mymovie.core.domain.model.AllData
import submission.dicoding.jetpack.mymovie.core.domain.model.FavoriteData
import submission.dicoding.jetpack.mymovie.core.domain.repo.IMyMovieRepo
import submission.dicoding.jetpack.mymovie.core.util.DataMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyMovieRepo @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMyMovieRepo {
    override fun getAllList(
        mediaType: String,
        category: String,
    ): Flow<PagingData<AllData>> =
        remoteDataSource.getAllList(mediaType, category).map {
            it.map { allResponse ->
                DataMapper.mapResponseToDomain(allResponse)
            }
        }


    override fun searchAllType(query: String): Flow<PagingData<AllData>> =
        remoteDataSource.searchAllType(query).map {
            it.map { allResponse ->
                DataMapper.mapResponseToDomain(allResponse)
            }
        }


    override suspend fun getDetailItem(mediaType: String, mediaId: Int): Resource<AllData> =
        when (val apiResponse = remoteDataSource.getItemDetail(mediaType, mediaId)) {
            is ApiResponse.Success ->
                Resource.success(
                    DataMapper.mapResponseToDomain(
                        apiResponse.data
                    )
                )

            is ApiResponse.Error -> Resource.error("error")
        }


    override fun getAllFavorite(mediaType: String): Flow<PagingData<FavoriteData>> =
        localDataSource.getAllFavorite(mediaType).map {
            it.map { favoriteEntity ->
                DataMapper.mapEntitiesToDomain(favoriteEntity)
            }
        }

    override fun getSumOfAllFavorite(mediaType: String): LiveData<Int> =
        localDataSource.getSumOfAllFavorite(mediaType)

    override fun isFavorite(id: Int): LiveData<Int> = localDataSource.isFavorite(id)

    override suspend fun insertFavorite(favoriteData: FavoriteData) =
        localDataSource.insertFavorite(DataMapper.mapDomainToEntities(favoriteData))

    override suspend fun deleteFavorite(favoriteData: FavoriteData) =
        localDataSource.deleteFavorite(DataMapper.mapDomainToEntities(favoriteData))


}