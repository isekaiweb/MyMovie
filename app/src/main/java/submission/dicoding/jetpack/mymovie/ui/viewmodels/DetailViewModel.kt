package submission.dicoding.jetpack.mymovie.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import submission.dicoding.jetpack.mymovie.models.FavoriteEntity
import submission.dicoding.jetpack.mymovie.repo.BaseFavoriteRepo
import submission.dicoding.jetpack.mymovie.models.MovieEntity
import submission.dicoding.jetpack.mymovie.repo.MovieRepo
import submission.dicoding.jetpack.mymovie.util.Resource
import submission.dicoding.jetpack.mymovie.util.SingleEvent
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepoImpl: MovieRepo,
    private val baseFavoriteRepo: BaseFavoriteRepo
) : ViewModel() {

    private val _movie = MutableLiveData<SingleEvent<Resource<MovieEntity>>>()
    val movieEntity: LiveData<SingleEvent<Resource<MovieEntity>>> get() = _movie

    private val _isSaved = MutableLiveData<SingleEvent<Boolean>>()
    val isSaved: LiveData<SingleEvent<Boolean>> get() = _isSaved

    fun getDetailMovie(mediaType: String, mediaId: Int) {
        _movie.value = SingleEvent(Resource.loading(null))
        viewModelScope.launch {
            val response = movieRepoImpl.getMovieDetail(mediaType, mediaId)
            _movie.value = SingleEvent(response)
        }
    }

    fun removeFavorite(favorite: FavoriteEntity) = viewModelScope.launch {
        baseFavoriteRepo.delete(favorite)
    }

    fun addFavorite(favorite: FavoriteEntity) = viewModelScope.launch {
        baseFavoriteRepo.insert(favorite)
    }

    fun checkFavorite(id: Int) = viewModelScope.launch {
        val count = baseFavoriteRepo.checkFavorite(id)
        if (count != 0) _isSaved.value = SingleEvent(true)
        else _isSaved.value = SingleEvent(false)
    }

}