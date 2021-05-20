package submission.dicoding.jetpack.mymovie.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
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
import submission.dicoding.jetpack.mymovie.api.MyMovieServices
import submission.dicoding.jetpack.mymovie.models.MovieEntity
import submission.dicoding.jetpack.mymovie.models.MoviePagingSource
import submission.dicoding.jetpack.mymovie.models.RemoteDataSource
import submission.dicoding.jetpack.mymovie.util.Constants.CONVERSION_ERROR
import submission.dicoding.jetpack.mymovie.util.Resource
import submission.dicoding.jetpack.mymovie.utils.FakeData
import submission.dicoding.jetpack.mymovie.utils.getOrAwaitValue

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class MovieRepoTest {

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource


    private lateinit var repo: MovieRepo

    @Mock
    private lateinit var moviePagingSource: MoviePagingSource

    @Mock
    private lateinit var services: MyMovieServices

    @Mock
    private lateinit var observer: Observer<PagingData<MovieEntity>>

    @Before
    fun setup() {
        repo = MovieRepo(remoteDataSource)
    }

    /**
     * Test, to get list movies from services with valid response
     * */

    @Test
    fun `get list movies should return movie paging data`() = runBlockingTest {
        `when`(remoteDataSource.getListMovies("movie", "now_playing", null)).thenReturn(
            moviePagingData()
        )
        val value = repo.getListMovies("movie", "now_playing", null)
        verify(remoteDataSource).getListMovies("movie", "now_playing", null)

        value.observeForever(observer)
        observer.onChanged(moviePagingData().getOrAwaitValue())

    }

    /**
     * Test, to search movies from services with valid response
     * */
    @Test
    fun `search movies should return movie paging data`() = runBlockingTest {
        `when`(remoteDataSource.getListMovies("search", "movie", "mortal")).thenReturn(
            moviePagingData()
        )
        val value = repo.getListMovies("search", "movie", "mortal")
        verify(remoteDataSource).getListMovies("search", "movie", "mortal")

        value.observeForever(observer)
        observer.onChanged(moviePagingData().getOrAwaitValue())

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

    /**
     * Test, to get detail movie from services with valid response
     * */
    @Test
    fun `get movie detail success`() = runBlockingTest {
        val movie = FakeData.createMovie
        val expect: Resource<MovieEntity> = Resource.success(movie)
        `when`(remoteDataSource.getMovieDetail("movie", 1)).thenReturn(expect)
        val value = repo.getMovieDetail("movie", 1).data

        verify(remoteDataSource).getMovieDetail("movie", 1)
        assertThat(value).isEqualTo(movie)
    }


    /**
     * Test, to get detail movie from services with invalid response
     * */
    @Test
    fun `get movie detail failed`() = runBlockingTest {
        val expect: Resource<MovieEntity> = Resource.error(CONVERSION_ERROR, null)
        `when`(remoteDataSource.getMovieDetail("movie", 1)).thenReturn(expect)
        val value = repo.getMovieDetail("movie", 1)

        verify(remoteDataSource).getMovieDetail("movie", 1)

        assertThat(value.message).isEqualTo(CONVERSION_ERROR)
        assertThat(value.data).isNull()
    }
}