package submission.dicoding.jetpack.mymovie.models


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import submission.dicoding.jetpack.mymovie.api.MyMovieServices
import submission.dicoding.jetpack.mymovie.util.Constants
import submission.dicoding.jetpack.mymovie.util.IdlingResource
import submission.dicoding.jetpack.mymovie.util.Resource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val services: MyMovieServices) {
    // idling resource needed for testing
    suspend fun getListMovies(mediaType: String, category: String, page: Int): Resource<MovieResponse> =
        try {
            IdlingResource.increment()
            val response =
                withContext(Dispatchers.IO) { services.getListMovies(mediaType, category, page) }
            IdlingResource.decrement()

            if (response.isSuccessful) {
                response.body()?.let { movieResponse ->
                    return@let Resource.success(movieResponse)
                } ?: Resource.error(Constants.CONVERSION_ERROR, null)
            } else {
                Resource.error(Constants.CONVERSION_ERROR, null)
            }
        } catch (e: Exception) {
            Resource.error(Constants.NETWORK_FAILURE, null)
        }

    suspend fun getMovieDetail(mediaType: String, mediaId: Int): Resource<Movie> =
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