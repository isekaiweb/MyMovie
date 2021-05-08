package submission.dicoding.jetpack.mymovie.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import submission.dicoding.jetpack.mymovie.BuildConfig.API_KEY
import submission.dicoding.jetpack.mymovie.models.Movie
import submission.dicoding.jetpack.mymovie.models.MovieResponse

interface MyMovieServices {
    @GET("{media_type}/{category}?api_key=$API_KEY")
    suspend fun getMovies(
        @Path("media_type") mediaType: String,
        @Path("category") category: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("{media_type}/{media_id}?api_key=$API_KEY")
    suspend fun getDetailMovie(
        @Path("media_type") mediaType: String,
        @Path("media_id") mediaId: Int,
    ): Response<Movie>
}