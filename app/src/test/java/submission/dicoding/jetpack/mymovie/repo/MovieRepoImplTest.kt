package submission.dicoding.jetpack.mymovie.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import submission.dicoding.jetpack.mymovie.models.RemoteDataSource
import submission.dicoding.jetpack.mymovie.util.Constants.CONVERSION_ERROR
import submission.dicoding.jetpack.mymovie.util.Constants.NETWORK_FAILURE
import submission.dicoding.jetpack.mymovie.util.Resource
import submission.dicoding.jetpack.mymovie.utils.FakeData

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class MovieRepoImplTest {

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var repoImpl: MovieRepoImpl

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        repoImpl = MovieRepoImpl(remoteDataSource)
    }

    @Test
    fun `get list movies success`() = runBlockingTest {
        val movies = MovieResponse(listOf(FakeData.createMovie()))
        val expect: Resource<MovieResponse> = Resource.success(movies)
        `when`(remoteDataSource.getListMovies("movie", "now_playing", 1)).thenReturn(expect)
        val value = repoImpl.getListMovies("movie", "now_playing", 1).data

        verify(remoteDataSource).getListMovies("movie", "now_playing", 1)
        assertThat(value).isEqualTo(movies)
    }

    @Test
    fun `get list movies failed`() = runBlockingTest {
        val expect: Resource<MovieResponse> = Resource.error(NETWORK_FAILURE, null)
        `when`(remoteDataSource.getListMovies("movie", "now_playing", 1)).thenReturn(expect)
        val value = repoImpl.getListMovies("movie", "now_playing", 1)

        verify(remoteDataSource).getListMovies("movie", "now_playing", 1)
        assertThat(value.message).isEqualTo(NETWORK_FAILURE)
        assertThat(value.data).isNull()
    }


    @Test
    fun `get movie detail success`() = runBlockingTest {
        val movie = FakeData.createMovie()
        val expect: Resource<Movie> = Resource.success(movie)
        `when`(remoteDataSource.getMovieDetail("movie", 1)).thenReturn(expect)
        val value = repoImpl.getMovieDetail("movie", 1).data

        verify(remoteDataSource).getMovieDetail("movie", 1)
        assertThat(value).isEqualTo(movie)
    }


    @Test
    fun `get movie detail failed`() = runBlockingTest {
        val expect: Resource<Movie> = Resource.error(CONVERSION_ERROR, null)
        `when`(remoteDataSource.getMovieDetail("movie", 1)).thenReturn(expect)
        val value = repoImpl.getMovieDetail("movie", 1)

        verify(remoteDataSource).getMovieDetail("movie", 1)

        assertThat(value.message).isEqualTo(CONVERSION_ERROR)
        assertThat(value.data).isNull()
    }
}