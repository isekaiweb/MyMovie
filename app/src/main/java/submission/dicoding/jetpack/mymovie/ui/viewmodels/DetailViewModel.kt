package submission.dicoding.jetpack.mymovie.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import submission.dicoding.jetpack.mymovie.models.Movie
import submission.dicoding.jetpack.mymovie.repo.MovieRepo
import submission.dicoding.jetpack.mymovie.util.*
import submission.dicoding.jetpack.mymovie.util.Constants.CONVERSION_ERROR
import submission.dicoding.jetpack.mymovie.util.Constants.NETWORK_FAILURE
import submission.dicoding.jetpack.mymovie.util.Constants.NO_INTERNET_CONNECTION
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepo: MovieRepo,
    app: Application
) : ConnectionChecker(app) {

    private val _movie = MutableLiveData<SingleEvent<Resource<Movie>>>()
    val movie: LiveData<SingleEvent<Resource<Movie>>> get() = _movie


    fun getDetailMovie(mediaType: String, mediaId: Int) = viewModelScope.launch {
        safeGetDetailUser(mediaType, mediaId)
    }

    private fun handleResponseGetDetailMovie(response: Response<Movie>): Resource<Movie> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource(Status.SUCCESS, result, null)
            }
        }
        return Resource(Status.ERROR, null, response.message())
    }

    private suspend fun safeGetDetailUser(mediaType: String, mediaId: Int) {
        _movie.postValue(SingleEvent(Resource(Status.LOADING, null, null)))

        // idling resource needed for testing
        try {
            IdlingResource.increment()
            if (hasInternetConnection()) {
                val response = movieRepo.getDetailMovie(mediaType, mediaId)
                _movie.postValue(SingleEvent(handleResponseGetDetailMovie(response)))
            } else {
                _movie.postValue(SingleEvent(Resource(Status.ERROR, null, NO_INTERNET_CONNECTION)))
            }
        } catch (e: Throwable) {
            if (e is IOException) {
                _movie.postValue(SingleEvent(Resource(Status.ERROR, null, CONVERSION_ERROR)))
            } else _movie.postValue(SingleEvent(Resource(Status.ERROR, null, NETWORK_FAILURE)))
        } finally {
            IdlingResource.decrement()
        }
    }
}