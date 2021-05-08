package submission.dicoding.jetpack.mymovie.models

data class MovieResponse(
    val results: List<Movie>,
)

data class Movie(
    /* Movie */
    var title: String? = null,
    var release_date: String? = null,

    /* TV */
    var name: String? = null,
    var first_air_date: String? = null,

    /* General */
    val id: Int,
    val overview: String,
    val poster_path: String,
)