package submission.dicoding.jetpack.mymovie.core.data.source.remote.response

data class AllResponse(
    /* Movie */
    var title: String? = null,
    var release_date: String? = null,

    /* Series */
    var name: String? = null,
    var first_air_date: String? = null,

    /* General */
    val id: Int,
    val overview: String? = null,
    val poster_path: String? = null,
    var isFavorite: Boolean = false
)