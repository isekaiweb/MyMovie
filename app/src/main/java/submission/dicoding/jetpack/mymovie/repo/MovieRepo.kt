package submission.dicoding.jetpack.mymovie.repo

import submission.dicoding.jetpack.mymovie.models.Movie
import submission.dicoding.jetpack.mymovie.models.MovieResponse
import submission.dicoding.jetpack.mymovie.util.Resource

interface MovieRepo {
    suspend fun getListMovies(mediaType: String, category: String, page: Int): Resource<MovieResponse>
    suspend fun getMovieDetail(
        mediaType: String,
        mediaId: Int
    ): Resource<Movie>
}