package submission.dicoding.jetpack.mymovie.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
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
import submission.dicoding.jetpack.mymovie.models.Movie
import submission.dicoding.jetpack.mymovie.models.MovieResponse
import submission.dicoding.jetpack.mymovie.repo.MoviePagingSource
import submission.dicoding.jetpack.mymovie.repo.MovieRepoImpl
import submission.dicoding.jetpack.mymovie.util.Constants.NETWORK_FAILURE
import submission.dicoding.jetpack.mymovie.util.Resource
import submission.dicoding.jetpack.mymovie.utils.FakeData
import submission.dicoding.jetpack.mymovie.utils.getOrAwaitValue

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class ListViewModelTest {
    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repoImpl: MovieRepoImpl
    private lateinit var viewModel: ListViewModel

    private lateinit var moviePagingSource: MoviePagingSource

    @Mock
    private lateinit var params: PagingSource.LoadParams<Int>

    @Mock
    private lateinit var listObserver: Observer<PagingData<Movie>>


    @Before
    fun setup() {
        moviePagingSource = MoviePagingSource(repoImpl, "movie", "now_playing")
        viewModel = ListViewModel(repoImpl)
    }

    /**
     * Test, observe get list movies with success resource and validate if data would return correctly
     * */


    @Test
    fun `get list movies observe handling success resource`() = runBlockingTest {
        val movies = MovieResponse(listOf(FakeData.createMovie()))
        val expect = Resource.success(movies)
        `when`(repoImpl.getListMovies("movie", "now_playing", 1)).thenReturn(expect)

        val response = viewModel.getListMovies("movie", "now_playing")
        val value = moviePagingSource.load(params)
        verify(repoImpl).getListMovies("movie", "now_playing", 1)

        response.observeForever(listObserver)
        verify(listObserver).onChanged(response.getOrAwaitValue())

        assertThat(response).isNotNull()


        assertThat(value).isEqualTo(
            PagingSource.LoadResult.Page(
                data = movies.results,
                null,
                2
            )
        )

    }

    /**
     * Test, observe get list movies with failure resource and validate if data wouldn't return correctly
     * */

    @Test
    fun `get list movies observe handling error resource`() = runBlockingTest {
        val movies = MovieResponse(listOf(FakeData.createMovie()))
        val expect = Resource.error(NETWORK_FAILURE, null)
        `when`(repoImpl.getListMovies("movie", "now_playing", 1)).thenReturn(expect)

        val response = viewModel.getListMovies("movie", "now_playing")
        val value = moviePagingSource.load(params)
        verify(repoImpl).getListMovies("movie", "now_playing", 1)

        response.observeForever(listObserver)
        verify(listObserver).onChanged(response.getOrAwaitValue())

        assertThat(response).isNotNull()
        assertThat(value).isNotEqualTo(
            PagingSource.LoadResult.Page(
                data = movies.results,
                null,
                2
            )
        )

    }

}