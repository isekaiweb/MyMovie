package submission.dicoding.jetpack.mymovie.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import submission.dicoding.jetpack.mymovie.repo.BaseFavoriteRepo
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repo: BaseFavoriteRepo
) : ViewModel() {
    fun getAllMovie(mediaType: String) = repo.getAllFavorite(mediaType).cachedIn(viewModelScope)
    fun totalFavorite(mediaType: String) = repo.countFavorite(mediaType)

}