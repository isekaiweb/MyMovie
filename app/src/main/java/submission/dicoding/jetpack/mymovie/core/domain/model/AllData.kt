package submission.dicoding.jetpack.mymovie.core.domain.model


data class AllData(
    val id: Int,
    val title: String,
    val date: String,
    val overview: String,
    val poster_path: String,
    val media_type: String,
)