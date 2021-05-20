package submission.dicoding.jetpack.mymovie.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import submission.dicoding.jetpack.mymovie.models.FavoriteEntity

class FakePagingSource : PagingSource<Int, FavoriteEntity>() {

    private val favorites = mutableListOf(FakeData.createFavorite)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FavoriteEntity> =
        LoadResult.Page(favorites, null, null)


    override fun getRefreshKey(state: PagingState<Int, FavoriteEntity>)
            : Int? = state.anchorPosition


}