package submission.dicoding.jetpack.mymovie.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import submission.dicoding.jetpack.mymovie.core.domain.model.AllData
import submission.dicoding.jetpack.mymovie.ui.main.ListViewModel
import submission.dicoding.jetpack.mymovie.usecase.MyMovieInteractorTest
import submission.dicoding.jetpack.mymovie.util.MainCoroutinesRule
import submission.dicoding.jetpack.mymovie.util.getOrAwaitValue

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewmodel: SearchViewModel

    @Mock
    private lateinit var observe: Observer<PagingData<AllData>>

    @Before
    fun setup() {
        viewmodel = SearchViewModel(MyMovieInteractorTest())
    }

    @Test
    fun `should return observe pagingData`() {
        val value = viewmodel.searchAll("avengers")

        value.observeForever(observe)
        observe.onChanged(value.getOrAwaitValue())
    }
}