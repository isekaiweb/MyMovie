package submission.dicoding.jetpack.mymovie.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import submission.dicoding.jetpack.mymovie.repo.MovieRepo
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {
    fun getMovie(mediaType: String, category: String) =
        movieRepo.getMovies(mediaType, category).getContentIfNotHandled()?.cachedIn(viewModelScope)
}