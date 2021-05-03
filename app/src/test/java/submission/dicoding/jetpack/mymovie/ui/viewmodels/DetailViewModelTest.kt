package submission.dicoding.jetpack.mymovie.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import submission.dicoding.jetpack.mymovie.getOrAwaitValueTest
import submission.dicoding.jetpack.mymovie.repo.FakeData
import submission.dicoding.jetpack.mymovie.repo.FakeRepo

class DetailViewModelTest {
    private lateinit var testViewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        testViewModel = DetailViewModel(FakeRepo())
    }

    /**
    Test, to check if data from movie and tv successfully combined
     */

    @Test
    fun `all data Movie success to combine`() {
        assertThat(testViewModel.allDataMovie.size).isEqualTo(2)
    }

    /**
    Test, to check if data successfully transfer to DetailFragment with right id
     */
    @Test
    fun `data detail successfully get by passing true id`() {
        testViewModel.setMovieDetail(11)
        assertThat(testViewModel.movieDetail.getOrAwaitValueTest()).isEqualTo(FakeData.tv()[0])
    }

    /**
    Test, to check if data failed transfer to DetailFragment with wrong id
     */
    @Test
    fun `data detail failed with wrong id`() {
        testViewModel.setMovieDetail(12)
        assertThat(testViewModel.movieDetail.getOrAwaitValueTest()).isNotEqualTo(FakeData.tv()[0])
    }
}