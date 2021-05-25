package submission.dicoding.jetpack.mymovie.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import submission.dicoding.jetpack.mymovie.core.domain.model.FavoriteData
import submission.dicoding.jetpack.mymovie.core.domain.usecase.MyMovieUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: MyMovieUseCase
) : ViewModel() {

    fun getDetailItem(mediaType: String, mediaId: Int) =
        useCase.getDetailItem(mediaType, mediaId).asLiveData()

    fun isFavorite(id: Int) = useCase.isFavorite(id).asLiveData()

    fun deleteFavorite(favorite: FavoriteData) = viewModelScope.launch {
        useCase.deleteFavorite(favorite)
    }

    fun insertFavorite(favorite: FavoriteData) = viewModelScope.launch {
        useCase.insertFavorite(favorite)
    }

}