package submission.dicoding.jetpack.mymovie.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import submission.dicoding.jetpack.mymovie.repo.MoviePagingSource
import submission.dicoding.jetpack.mymovie.repo.MovieRepoImpl
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val movieRepoImpl: MovieRepoImpl
) : ViewModel() {
    fun getListMovies(mediaType: String, category: String) =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(movieRepoImpl, mediaType, category) }
        ).liveData.cachedIn(viewModelScope)


}