package submission.dicoding.jetpack.mymovie.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import submission.dicoding.jetpack.mymovie.util.Constants.TABLE_NAME_FAVORITE

@Entity(tableName = TABLE_NAME_FAVORITE)
@Parcelize
data class FavoriteEntity(
    @PrimaryKey
    val id: Int,
    val poster_path: String,
    val media_type: String,
    val title: String,
) : Parcelable