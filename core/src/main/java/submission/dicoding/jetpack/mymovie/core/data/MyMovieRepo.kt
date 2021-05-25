package submission.dicoding.jetpack.mymovie.core.data

import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.*
import submission.dicoding.jetpack.mymovie.core.data.source.local.LocalDataSource
import submission.dicoding.jetpack.mymovie.core.data.source.remote.RemoteDataSource
import submission.dicoding.jetpack.mymovie.core.data.source.remote.network.ApiResponse
import submission.dicoding.jetpack.mymovie.core.domain.model.AllData
import submission.dicoding.jetpack.mymovie.core.domain.model.FavoriteData
import submission.dicoding.jetpack.mymovie.core.domain.repo.IMyMovieRepo
import submission.dicoding.jetpack.mymovie.core.util.DataMapper
import submission.dicoding.jetpack.mymovie.core.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyMovieRepo @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: submission.dicoding.jetpack.mymovie.core.data.source.local.LocalDataSource
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


    override fun getDetailItem(mediaType: String, mediaId: Int): Flow<Resource<AllData>> =
        flow {
            emit(Resource.loading())
            when (val apiResponse = remoteDataSource.getItemDetail(mediaType, mediaId).first()) {
                is ApiResponse.Success ->
                    emit(
                        Resource.success(
                            DataMapper.mapResponseToDomain(
                                apiResponse.data
                            )
                        )
                    )
                is ApiResponse.Error -> emit(Resource.error<AllData>(apiResponse.errorMessage))
            }
        }


    override fun getAllFavorite(mediaType: String): Flow<PagingData<FavoriteData>> =
        localDataSource.getAllFavorite(mediaType).map {
            it.map { favoriteEntity ->
                DataMapper.mapEntitiesToDomain(favoriteEntity)
            }
        }

    override fun getSumOfAllFavorite(mediaType: String): Flow<Int> =
        localDataSource.getSumOfAllFavorite(mediaType)

    override fun isFavorite(id: Int): Flow<Int> = localDataSource.isFavorite(id)

    override suspend fun insertFavorite(favoriteData: FavoriteData) =
        localDataSource.insertFavorite(DataMapper.mapDomainToEntities(favoriteData))

    override suspend fun deleteFavorite(favoriteData: FavoriteData) =
        localDataSource.deleteFavorite(DataMapper.mapDomainToEntities(favoriteData))


}