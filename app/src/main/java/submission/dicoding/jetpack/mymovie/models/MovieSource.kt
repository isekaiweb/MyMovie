package submission.dicoding.jetpack.mymovie.models

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import submission.dicoding.jetpack.mymovie.util.Resource

interface MovieSource {
    fun getListMovies(
        mediaType: String,
        category: String,
        query: String?
    ): LiveData<PagingData<MovieEntity>>

    suspend fun getMovieDetail(
        mediaType: String,
        mediaId: Int
    ): Resource<MovieEntity>
}