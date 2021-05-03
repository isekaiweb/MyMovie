package submission.dicoding.jetpack.mymovie.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import submission.dicoding.jetpack.mymovie.models.MovieResponse
import submission.dicoding.jetpack.mymovie.repo.MovieRepo
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    movieRepo: MovieRepo
) : ViewModel() {

    private val _movieDetail = MutableLiveData<MovieResponse>()
    val movieDetail: LiveData<MovieResponse> get() = _movieDetail

    private val _allDataMovie = movieRepo.generateDataMovie().plus(movieRepo.generateDataTv())
    val allDataMovie get() = _allDataMovie


    fun setMovieDetail(id: Int) {
        _movieDetail.postValue(_allDataMovie.find { it.id == id })
    }
}