package submission.dicoding.jetpack.mymovie.repo

import submission.dicoding.jetpack.mymovie.models.MovieResponse

interface MovieRepo {
    fun generateDataMovie(): List<MovieResponse>
    fun generateDataTv(): List<MovieResponse>
}