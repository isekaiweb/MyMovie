package submission.dicoding.jetpack.mymovie.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import submission.dicoding.jetpack.mymovie.models.Movie
import submission.dicoding.jetpack.mymovie.repo.MovieRepoImpl
import submission.dicoding.jetpack.mymovie.util.Resource
import submission.dicoding.jetpack.mymovie.util.SingleEvent
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepoImpl: MovieRepoImpl,
) : ViewModel() {

    private val _movie = MutableLiveData<SingleEvent<Resource<Movie>>>()
    val movie: LiveData<SingleEvent<Resource<Movie>>> get() = _movie

    fun getDetailMovie(mediaType: String, mediaId: Int) {
        _movie.value = SingleEvent(Resource.loading(null))
        viewModelScope.launch {
            val response = movieRepoImpl.getMovieDetail(mediaType, mediaId)
            _movie.value = SingleEvent(response)
        }
    }
}