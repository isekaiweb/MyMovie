package submission.dicoding.jetpack.mymovie.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import submission.dicoding.jetpack.mymovie.MainCoroutinesRule
import submission.dicoding.jetpack.mymovie.api.MyMovieServices
import submission.dicoding.jetpack.mymovie.models.MovieEntity
import submission.dicoding.jetpack.mymovie.models.MoviePagingSource
import submission.dicoding.jetpack.mymovie.repo.MovieRepo
import submission.dicoding.jetpack.mymovie.utils.getOrAwaitValue

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class ListViewModelTest {
    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repo: MovieRepo
    private lateinit var viewModel: ListViewModel

    @Mock
    private lateinit var observe:Observer<PagingData<MovieEntity>>


    @Mock
    private lateinit var services: MyMovieServices

    @Mock
    private lateinit var moviePagingSource: MoviePagingSource

    @Before
    fun setup() {
        viewModel = ListViewModel(repo)
    }

    /**
     * Test, if list movie be able to observe and return right data
     * */

    @Test
    fun `get list movies observe`() = runBlockingTest {
        `when`(repo.getListMovies("movie", "now_playing",null)).thenReturn(moviePagingData())
        val value = viewModel.getListMovies("movie", "now_playing",null)
        verify(repo).getListMovies("movie", "now_playing",null)

        value.observeForever(observe)
        observe.onChanged(moviePagingData().getOrAwaitValue())
    }

    /**
     * Test, if search movie be able to observe and return right data
     * */


    @Test
    fun `get search movies observe`() = runBlockingTest {
        `when`(repo.getListMovies("search", "movie","mortal")).thenReturn(moviePagingData())
        val value = viewModel.getListMovies("search", "movie","mortal")
        verify(repo).getListMovies("search", "movie","mortal")

        value.observeForever(observe)
        observe.onChanged(moviePagingData().getOrAwaitValue())
    }

    private fun moviePagingData(): LiveData<PagingData<MovieEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { moviePagingSource }
        ).liveData
    }
}