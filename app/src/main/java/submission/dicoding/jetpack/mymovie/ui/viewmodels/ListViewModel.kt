package submission.dicoding.jetpack.mymovie.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import submission.dicoding.jetpack.mymovie.models.MovieEntity
import submission.dicoding.jetpack.mymovie.repo.MovieRepo
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val movieRepoImpl: MovieRepo,
) : ViewModel() {
    fun getListMovies(
        mediaType: String,
        category: String,
        query: String?
    ): LiveData<PagingData<MovieEntity>> =
        movieRepoImpl.getListMovies(mediaType, category, query).cachedIn(viewModelScope)


}