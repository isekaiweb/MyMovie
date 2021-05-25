package submission.dicoding.jetpack.mymovie.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import submission.dicoding.jetpack.mymovie.core.domain.usecase.MyMovieUseCase
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    useCase: MyMovieUseCase
) : ViewModel() {
    val getAllFavorite =
        useCase.getAllFavorite().asLiveData().cachedIn(viewModelScope)
    val getSumOfAllFavorite = useCase.getSumOfAllFavorite().asLiveData()

}