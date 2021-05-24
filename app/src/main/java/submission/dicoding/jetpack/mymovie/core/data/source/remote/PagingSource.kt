package submission.dicoding.jetpack.mymovie.core.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import submission.dicoding.jetpack.mymovie.core.data.source.remote.network.ApiServices
import submission.dicoding.jetpack.mymovie.core.data.source.remote.response.AllResponse
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PagingSource @Inject constructor(
    private val services: ApiServices,
    private val mediaType: String? = null,
    private val category: String? = null,
    private val query: String? = null
) : PagingSource<Int, AllResponse>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AllResponse> {
        return try {
            val position = params.key ?: START_PAGE_INDEX
            val response =
                if (mediaType != null && category != null && query == null) services.getAllList(
                    mediaType,
                    category,
                    position
                ) else services.searchAllType(position, query)

            val allData = response.results

            LoadResult.Page(
                data = allData,
                prevKey = if (position == START_PAGE_INDEX) null else position - 1,
                nextKey = if (allData.isEmpty()) null else position + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, AllResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val START_PAGE_INDEX = 1
    }
}