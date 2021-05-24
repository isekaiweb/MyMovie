package submission.dicoding.jetpack.mymovie.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import submission.dicoding.jetpack.mymovie.core.domain.model.AllData
import submission.dicoding.jetpack.mymovie.core.domain.usecase.MyMovieUseCase
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCase: MyMovieUseCase
) : ViewModel() {
    fun getAllList(
        mediaType: String,
        category: String,
    ): LiveData<PagingData<AllData>> =
        useCase.getAllList(mediaType, category).asLiveData().cachedIn(viewModelScope)
}