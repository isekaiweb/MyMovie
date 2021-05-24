package submission.dicoding.jetpack.mymovie.ui.detail

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import submission.dicoding.jetpack.mymovie.core.data.Resource
import submission.dicoding.jetpack.mymovie.core.domain.model.AllData
import submission.dicoding.jetpack.mymovie.core.domain.model.FavoriteData
import submission.dicoding.jetpack.mymovie.core.domain.usecase.MyMovieUseCase
import submission.dicoding.jetpack.mymovie.util.SingleEvent
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: MyMovieUseCase
) : ViewModel() {

    private val _detail = MutableLiveData<SingleEvent<Resource<AllData>>>()
    val detail: LiveData<SingleEvent<Resource<AllData>>> get() = _detail

    fun getDetailItem(mediaType: String, mediaId: Int) =
        viewModelScope.launch {
            _detail.value = SingleEvent(useCase.getDetailItem(mediaType, mediaId))
        }

    fun isFavorite(id: Int) = useCase.isFavorite(id)

    fun deleteFavorite(favorite: FavoriteData) = viewModelScope.launch {
        useCase.deleteFavorite(favorite)
    }

    fun insertFavorite(favorite: FavoriteData) = viewModelScope.launch {
        useCase.insertFavorite(favorite)
    }

}