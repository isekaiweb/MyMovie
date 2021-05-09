package submission.dicoding.jetpack.mymovie.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import submission.dicoding.jetpack.mymovie.models.Movie
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviePagingSource @Inject constructor(
    private val movieRepoImpl: MovieRepoImpl,
    private val mediaType: String,
    private val category: String
) : PagingSource<Int, Movie>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val position = params.key ?: START_PAGE_INDEX

            val response = movieRepoImpl.getListMovies(mediaType, category, position)

            if (response.data?.results != null) {
                val movies = response.data.results
                LoadResult.Page(
                    data = movies,
                    prevKey = if (position == START_PAGE_INDEX) null else position - 1,
                    nextKey = if (movies.isEmpty()) null else position + 1
                )
            } else {
                val e = IOException("data null")
                LoadResult.Error(e)
            }


        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    companion object {
        const val START_PAGE_INDEX = 1
    }
}