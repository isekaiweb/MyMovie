package submission.dicoding.jetpack.mymovie.models

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import submission.dicoding.jetpack.mymovie.api.MyMovieServices
import submission.dicoding.jetpack.mymovie.util.IdlingResource
import java.io.IOException

class MoviePagingSource(
    private val services: MyMovieServices,
    private val mediaType: String,
    private val category: String
) : PagingSource<Int, Movie>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        // idling resource needed for testing
        return try {
            val position = params.key ?: START_PAGE_INDEX

            IdlingResource.increment()
            val response = services.getMovies(mediaType, category, position)
            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (position == START_PAGE_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } finally {
            IdlingResource.decrement()
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    companion object {
        const val START_PAGE_INDEX = 1
    }
}