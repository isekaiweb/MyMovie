package submission.dicoding.jetpack.mymovie.repo

class FakeRepo : MovieRepo {

    private val movie = FakeData.movie()
    private val tv = FakeData.tv()


    override fun generateDataMovie(): List<MovieResponse> = movie

    override fun generateDataTv(): List<MovieResponse> = tv
}