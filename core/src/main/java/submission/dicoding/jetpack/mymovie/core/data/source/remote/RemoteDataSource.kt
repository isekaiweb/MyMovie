package submission.dicoding.jetpack.mymovie.core.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import submission.dicoding.jetpack.mymovie.core.data.source.remote.network.ApiResponse
import submission.dicoding.jetpack.mymovie.core.data.source.remote.network.ApiServices
import submission.dicoding.jetpack.mymovie.core.data.source.remote.response.AllResponse
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val services: ApiServices) {
    fun getAllList(
        mediaType: String,
        category: String,
    ): Flow<PagingData<AllResponse>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PagingSource(
                    services,
                    mediaType,
                    category
                )
            }
        ).flow.map {
            it.filter { allResponse -> !allResponse.overview.isNullOrEmpty() && !allResponse.poster_path.isNullOrEmpty() }
        }.flowOn(Dispatchers.IO)

    fun searchAllType(
        query: String
    ): Flow<PagingData<AllResponse>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PagingSource(
                    services,
                    query = query
                )
            }
        ).flow.map {
            it.filter { allResponse -> !allResponse.overview.isNullOrEmpty() && !allResponse.poster_path.isNullOrEmpty() }
        }.flowOn(Dispatchers.IO)


    suspend fun getItemDetail(
        mediaType: String,
        mediaId: Int,
    ): Flow<ApiResponse<AllResponse>> =
        flow {
            try {
                val response = services.getItemDetail(mediaType, mediaId)
                emit(ApiResponse.Success(response))
            } catch (e: IOException) {
                Timber.e(e.toString())
                emit(ApiResponse.Error(e.toString()))
            } catch (e: HttpException) {
                Timber.e(e.toString())
                emit(ApiResponse.Error(e.toString()))
            }
        }
}