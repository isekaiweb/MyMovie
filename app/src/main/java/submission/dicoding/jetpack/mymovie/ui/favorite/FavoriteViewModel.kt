package submission.dicoding.jetpack.mymovie.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import submission.dicoding.jetpack.mymovie.core.domain.usecase.MyMovieUseCase
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCase: MyMovieUseCase
) : ViewModel() {
    fun getAllMovie(mediaType: String) =
        useCase.getAllFavorite(mediaType).asLiveData().cachedIn(viewModelScope)

    fun getSumOfAllFavorite(mediaType: String) = useCase.getSumOfAllFavorite(mediaType).asLiveData()

}