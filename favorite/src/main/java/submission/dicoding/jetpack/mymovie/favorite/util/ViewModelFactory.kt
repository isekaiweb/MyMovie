package submission.dicoding.jetpack.mymovie.favorite.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import submission.dicoding.jetpack.mymovie.core.domain.usecase.MyMovieUseCase
import submission.dicoding.jetpack.mymovie.favorite.ui.FavoriteViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val useCase: MyMovieUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(useCase) as T
            }
            else -> throw Throwable("unknown ViewModel class: ${modelClass.name}")
        }
}