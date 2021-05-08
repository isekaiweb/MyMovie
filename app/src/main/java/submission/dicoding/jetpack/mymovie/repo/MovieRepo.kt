package submission.dicoding.jetpack.mymovie.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import submission.dicoding.jetpack.mymovie.api.MyMovieServices
import submission.dicoding.jetpack.mymovie.models.MoviePagingSource
import submission.dicoding.jetpack.mymovie.util.SingleEvent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepo @Inject constructor(
    private val services: MyMovieServices
) {
    fun getMovies(mediaType: String, category: String) = SingleEvent(Pager(
        config = PagingConfig(
            pageSize = 5,
            maxSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { MoviePagingSource(services, mediaType, category) }
    ).liveData)

    suspend fun getDetailMovie(mediaType: String, mediaId: Int) =
        services.getDetailMovie(mediaType, mediaId)
}