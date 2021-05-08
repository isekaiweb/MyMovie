package submission.dicoding.jetpack.mymovie.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import submission.dicoding.jetpack.mymovie.MainCoroutinesRule
import java.io.IOException

@ExperimentalCoroutinesApi
class MyMovieServicesTest : ApiAbstract<MyMovieServices>() {

    @get: Rule
    val coroutinesRule = MainCoroutinesRule()

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: MyMovieServices

    @Before
    fun initService() {
        service = createService(MyMovieServices::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun `get movie from network and verify data`() = runBlocking {
        enqueueResponse("/MovieResponse.json")
        val response = service.getMovies("movie", "now_playing", 1)
        val responseBody = requireNotNull(response.results)
        mockWebServer.takeRequest()

        val value = responseBody[0]
        assertThat(responseBody.count()).isEqualTo(20)
        assertThat(value.id).isEqualTo(460465)
        assertThat(value.title).isEqualTo("Mortal Kombat")
        assertThat(value.overview).isEqualTo("Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.")
        assertThat(value.release_date).isEqualTo("2021-04-07")
    }

    @Throws(IOException::class)
    @Test
    fun `get movie detail from network and verify data`() = runBlocking {
        enqueueResponse("/MovieDetailResponse.json")
        val response = service.getDetailMovie("movie", 460465)
        mockWebServer.takeRequest()

        val value = response.body()
        assertThat(value?.id).isEqualTo(460465)
        assertThat(value?.title).isEqualTo("Mortal Kombat")
        assertThat(value?.overview).isEqualTo("Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.")
        assertThat(value?.release_date).isEqualTo("2021-04-07")

    }
}