package submission.dicoding.jetpack.mymovie.models

data class MovieResponse(
    val results: List<MovieEntity>,
)

data class MovieEntity(
    /* Movie */
    var title: String? = null,
    var release_date: String? = null,

    /* TV */
    var name: String? = null,
    var first_air_date: String? = null,

    /* General */
    val id: Int,
    val overview: String? = null,
    val poster_path: String? = null,
)