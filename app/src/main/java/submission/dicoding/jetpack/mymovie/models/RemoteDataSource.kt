package submission.dicoding.jetpack.mymovie.models


import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import submission.dicoding.jetpack.mymovie.api.MyMovieServices
import submission.dicoding.jetpack.mymovie.util.Constants
import submission.dicoding.jetpack.mymovie.util.IdlingResource
import submission.dicoding.jetpack.mymovie.util.Resource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val services: MyMovieServices) : MovieSource {
    override fun getListMovies(
        mediaType: String,
        category: String,
        query: String?
    ): LiveData<PagingData<MovieEntity>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(services, mediaType, category, query) }
        ).flow.map {
            it.filter { movie -> !movie.overview.isNullOrEmpty() && !movie.overview.isNullOrEmpty() }
        }.flowOn(Dispatchers.IO).asLiveData()

    // idling resource needed for testing
    override suspend fun getMovieDetail(mediaType: String, mediaId: Int): Resource<MovieEntity> =
        try {
            IdlingResource.increment()
            val response =
                withContext(Dispatchers.IO) { services.getDetailMovie(mediaType, mediaId) }
            IdlingResource.decrement()

            if (response.isSuccessful) {
                response.body()?.let { movie ->
                    return@let Resource.success(movie)
                } ?: Resource.error(Constants.CONVERSION_ERROR, null)
            } else {
                Resource.error(Constants.CONVERSION_ERROR, null)
            }
        } catch (e: Exception) {
            Resource.error(Constants.NETWORK_FAILURE, null)
        }

}