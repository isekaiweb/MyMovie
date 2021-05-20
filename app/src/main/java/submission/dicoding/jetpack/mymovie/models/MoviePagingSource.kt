package submission.dicoding.jetpack.mymovie.models

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import submission.dicoding.jetpack.mymovie.api.MyMovieServices
import submission.dicoding.jetpack.mymovie.util.IdlingResource
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviePagingSource @Inject constructor(
    private val myMovieServices: MyMovieServices,
    private val mediaType: String,
    private val category: String,
    private val query: String?
) : PagingSource<Int, MovieEntity>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return try {
            IdlingResource.increment()
            val position = params.key ?: START_PAGE_INDEX
            val response = myMovieServices.getListMovies(mediaType, category, position, query)

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


    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val START_PAGE_INDEX = 1
    }
}