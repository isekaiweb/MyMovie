package submission.dicoding.jetpack.mymovie.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
      val id: Int,
      val title: String,
      val poster_url: String,
      val overview: String,
      val date_publish: String
) : Parcelable