package submission.dicoding.jetpack.mymovie.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import submission.dicoding.jetpack.mymovie.models.MovieEntity
import submission.dicoding.jetpack.mymovie.models.MovieSource
import submission.dicoding.jetpack.mymovie.models.RemoteDataSource
import submission.dicoding.jetpack.mymovie.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepo @Inject constructor(
    private val resource: RemoteDataSource
) : MovieSource {

    override fun getListMovies(
        mediaType: String,
        category: String,
        query: String?
    ): LiveData<PagingData<MovieEntity>> =
        resource.getListMovies(mediaType, category, query)

    override suspend fun getMovieDetail(mediaType: String, mediaId: Int): Resource<MovieEntity> =
        resource.getMovieDetail(mediaType, mediaId)
}