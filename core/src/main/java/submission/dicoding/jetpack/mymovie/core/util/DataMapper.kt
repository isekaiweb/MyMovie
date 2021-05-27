package submission.dicoding.jetpack.mymovie.core.util

import submission.dicoding.jetpack.mymovie.core.data.source.local.entity.FavoriteEntity
import submission.dicoding.jetpack.mymovie.core.data.source.remote.response.AllResponse
import submission.dicoding.jetpack.mymovie.core.domain.model.AllData
import submission.dicoding.jetpack.mymovie.core.domain.model.FavoriteData

object DataMapper {
    fun allDataToFavorite(input: AllData) = FavoriteData(
        id = input.id,
        poster_path = input.poster_path,
        media_type = input.media_type,
        title = input.title,
    )

    fun mapEntitiesToDomain(input: FavoriteEntity) =
        FavoriteData(
            id = input.id,
            poster_path = input.poster_path,
            media_type = input.media_type,
            title = input.title,
        )


    fun mapDomainToEntities(input: FavoriteData) =
        FavoriteEntity(
            id = input.id,
            poster_path = input.poster_path,
            media_type = input.media_type,
            title = input.title,
        )

    fun mapResponseToDomain(input: AllResponse) = AllData(
        id = input.id,
        poster_path = input.poster_path ?: "",
        title = input.title ?: input.name ?: "",
        overview = input.overview ?: "",
        date = input.first_air_date ?: input.release_date ?: "",
        media_type = if (input.title.isNullOrEmpty()) "tv" else "movie",
    )
}