package submission.dicoding.jetpack.mymovie.repo

import submission.dicoding.jetpack.mymovie.models.Movie
import submission.dicoding.jetpack.mymovie.models.MovieResponse
import submission.dicoding.jetpack.mymovie.models.RemoteDataSource
import submission.dicoding.jetpack.mymovie.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepoImpl @Inject constructor(
    private val resource: RemoteDataSource
) : MovieRepo {

    override suspend fun getListMovies(
        mediaType: String,
        category: String,
        page: Int
    ): Resource<MovieResponse> = resource.getListMovies(mediaType, category, page)


    override suspend fun getMovieDetail(mediaType: String, mediaId: Int): Resource<Movie> =
        resource.getMovieDetail(mediaType, mediaId)

}