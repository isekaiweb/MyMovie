package submission.dicoding.jetpack.mymovie.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import submission.dicoding.jetpack.mymovie.MainCoroutinesRule
import submission.dicoding.jetpack.mymovie.models.FavoriteEntity
import submission.dicoding.jetpack.mymovie.repo.FakeFavoriteRepo
import submission.dicoding.jetpack.mymovie.utils.getOrAwaitValue

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class FavoriteViewModelTest {
    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observeTotalFavorite: Observer<Int>

    @Mock
    private lateinit var observerAllFavorite: Observer<PagingData<FavoriteEntity>>
    private lateinit var viewModel: FavoriteViewModel

    @Before
    fun setup() {
        viewModel = FavoriteViewModel(FakeFavoriteRepo())
    }

    /**
     * Test, check if be able to return total of movie favorite in database
     * since it's fake repository the return always 0
     * */

    @Test
    fun `check function to know how many movies saved in database if be able to observe`() {
        val value = viewModel.totalFavorite("movie")

        value.observeForever(observeTotalFavorite)
        observeTotalFavorite.onChanged(value.getOrAwaitValue())

        assertThat(value.getOrAwaitValue()).isEqualTo(0)
    }

    /**
     * Test, check if be able to observe all favorite
     * */

    @Test
    fun `check function to get all movies saved in database if be able to observe`() {
        val value = viewModel.getAllMovie("movie")

        value.observeForever(observerAllFavorite)
        observerAllFavorite.onChanged(value.getOrAwaitValue())
    }

}