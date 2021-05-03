package submission.dicoding.jetpack.mymovie.repo

import submission.dicoding.jetpack.mymovie.models.MovieResponse
import javax.inject.Inject
import javax.inject.Named

class DefaultRepo @Inject constructor(
    @Named("movie") private val movie: List<MovieResponse>,
    @Named("tv") private val tv: List<MovieResponse>
) : MovieRepo {
    override fun generateDataMovie(): List<MovieResponse> = movie

    override fun generateDataTv(): List<MovieResponse> = tv

}