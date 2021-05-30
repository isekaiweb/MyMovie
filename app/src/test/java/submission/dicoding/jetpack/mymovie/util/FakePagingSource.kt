package submission.dicoding.jetpack.mymovie.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import submission.dicoding.jetpack.mymovie.core.domain.model.AllData
import submission.dicoding.jetpack.mymovie.core.domain.model.FavoriteData
import submission.dicoding.jetpack.mymovie.util.FakeData

class FakePagingSourceAllData : PagingSource<Int, AllData>() {
    private val data = mutableListOf(FakeData.createAllData)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AllData> =
        LoadResult.Page(data, null, null)

    override fun getRefreshKey(state: PagingState<Int, AllData>): Int? = state.anchorPosition

}

class FakePagingSourceFavoriteData(private val fav: MutableList<FavoriteData>) :
    PagingSource<Int, FavoriteData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FavoriteData> =
        LoadResult.Page(fav, null, null)

    override fun getRefreshKey(state: PagingState<Int, FavoriteData>): Int? = state.anchorPosition

}