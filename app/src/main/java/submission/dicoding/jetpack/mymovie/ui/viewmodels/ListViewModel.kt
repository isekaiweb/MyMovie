package submission.dicoding.jetpack.mymovie.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import submission.dicoding.jetpack.mymovie.models.MovieResponse
import submission.dicoding.jetpack.mymovie.repo.MovieRepo
import javax.inject.Inject


class ListViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {

    private val _currTab = MutableLiveData<List<MovieResponse>>()
    val currTab: LiveData<List<MovieResponse>> get() = _currTab


    fun setData(type: Int) {
        if (type == 0) _currTab.postValue(movieRepo.generateDataMovie())
        else if (type == 1) _currTab.postValue(movieRepo.generateDataTv())
        return
    }

}