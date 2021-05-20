package submission.dicoding.jetpack.mymovie.ui.viewmodels

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
import submission.dicoding.jetpack.mymovie.models.MovieEntity
import submission.dicoding.jetpack.mymovie.repo.FakeFavoriteRepo
import submission.dicoding.jetpack.mymovie.repo.MovieRepo
import submission.dicoding.jetpack.mymovie.util.Constants.NETWORK_FAILURE
import submission.dicoding.jetpack.mymovie.util.Resource
import submission.dicoding.jetpack.mymovie.util.Status
import submission.dicoding.jetpack.mymovie.utils.FakeData
import submission.dicoding.jetpack.mymovie.utils.getOrAwaitValue

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class DetailViewModelTest {
    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepo: MovieRepo
    private lateinit var viewModel: DetailViewModel


    @Before
    fun setup() {
        viewModel = DetailViewModel(movieRepo, FakeFavoriteRepo())
    }


    /**
     * Test, insert favorite movie in database and verify data
     * if successfully saved in database
     **/

    @Test
    fun `insert movie in favorite and verify data`() = runBlockingTest {
        val movie = FakeData.createFavorite
        viewModel.addFavorite(movie)
        viewModel.checkFavorite(movie.id)
        val value = viewModel.isSaved.getOrAwaitValue().getContentIfNotHandled()

        assertThat(value).isEqualTo(true)
    }


    /**
     * Test, Remove favorite movie in database and verify
     * if successfully removed
     * */

    @Test
    fun `insert and then delete movie favorite`() = runBlockingTest {
        val movie = FakeData.createFavorite
        viewModel.addFavorite(movie)
        viewModel.checkFavorite(movie.id)
        val valueBefore = viewModel.isSaved.getOrAwaitValue().getContentIfNotHandled()

        assertThat(valueBefore).isEqualTo(true)

        viewModel.removeFavorite(movie)
        viewModel.checkFavorite(movie.id)
        val valueAfter = viewModel.isSaved.getOrAwaitValue().getContentIfNotHandled()

        assertThat(valueAfter).isEqualTo(false)
    }

    /**
     * Test, observe get detail movie with success resource and verify if data is correctly
     * */

    @Test
    fun `get detail movie observe handling success resource`() = runBlockingTest {
        val movie = FakeData.createMovie
        val expect: Resource<MovieEntity> = Resource.success(movie)
        `when`(movieRepo.getMovieDetail("movie", 1)).thenReturn(expect)
        viewModel.getDetailMovie("movie", 1)
        val value = viewModel.movieEntity.getOrAwaitValue().getContentIfNotHandled()

        verify(movieRepo).getMovieDetail("movie", 1)
        assertThat(value?.status).isEqualTo(Status.SUCCESS)
        assertThat(value?.data).isEqualTo(movie)
    }

    /**
     * Test, observe get detail movie with failure resource and verify if data is empty
     * */
    @Test
    fun `get detail movie observe handling error resource`() = runBlockingTest {
        val expect: Resource<MovieEntity> = Resource.error(NETWORK_FAILURE, null)
        `when`(movieRepo.getMovieDetail("movie", 1)).thenReturn(expect)
        viewModel.getDetailMovie("movie", 1)
        val value = viewModel.movieEntity.getOrAwaitValue().getContentIfNotHandled()

        verify(movieRepo).getMovieDetail("movie", 1)
        assertThat(value?.status).isEqualTo(Status.ERROR)
        assertThat(value?.message).isEqualTo(NETWORK_FAILURE)
        assertThat(value?.data).isNull()
    }
}