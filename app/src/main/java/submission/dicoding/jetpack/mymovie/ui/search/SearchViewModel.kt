package submission.dicoding.jetpack.mymovie.ui.search

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
class SearchViewModel @Inject constructor(
    private val useCase: MyMovieUseCase
) : ViewModel() {
    fun searchAll(
        query: String
    ): LiveData<PagingData<AllData>> =
        useCase.searchAllType(query).asLiveData().cachedIn(viewModelScope)
}