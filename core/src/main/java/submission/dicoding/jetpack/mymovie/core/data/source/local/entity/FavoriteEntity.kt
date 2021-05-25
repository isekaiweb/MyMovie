package submission.dicoding.jetpack.mymovie.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
class FavoriteEntity(
    @PrimaryKey
    val id: Int,
    val poster_path: String,
    val media_type: String,
    val title: String,
)
