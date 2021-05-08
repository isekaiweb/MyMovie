package submission.dicoding.jetpack.mymovie.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response
import submission.dicoding.jetpack.mymovie.MainCoroutinesRule
import submission.dicoding.jetpack.mymovie.api.MyMovieServices
import submission.dicoding.jetpack.mymovie.models.Movie
import submission.dicoding.jetpack.mymovie.utils.FakeData

@ExperimentalCoroutinesApi
class MovieRepoTest {

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var repo: MovieRepo
    private lateinit var services: MyMovieServices

    @Before
    fun setup() {
        services = mock(MyMovieServices::class.java)
        repo = MovieRepo(services)
    }


    @Test
    fun `get movie detail success`() = runBlocking {
        val movie = FakeData.createMovie()[0]
        val expect: Response<Movie> = Response.success(movie)
        `when`(repo.getDetailMovie("movie", 1)).thenReturn(expect)
        val value = repo.getDetailMovie("movie", 1).body()

        assertThat(value?.id).isEqualTo(movie.id)
        assertThat(value?.overview).isEqualTo(movie.overview)
        assertThat(value?.poster_path).isEqualTo(movie.poster_path)
    }


    @Test
    fun `get movie detail failed`() = runBlocking {
        val expect: Response<Movie> = Response.error(404, "".toByteArray().toResponseBody(null))
        `when`(repo.getDetailMovie("movie", 1)).thenReturn(expect)
        val value = repo.getDetailMovie("movie", 1).body()

        assertThat(value).isNull()
    }
}