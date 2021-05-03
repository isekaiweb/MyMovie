package submission.dicoding.jetpack.mymovie.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import submission.dicoding.jetpack.mymovie.getOrAwaitValueTest
import submission.dicoding.jetpack.mymovie.repo.FakeData
import submission.dicoding.jetpack.mymovie.repo.FakeRepo


class ListViewModelTest {
    private lateinit var testViewModel: ListViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        testViewModel = ListViewModel(FakeRepo())
    }

    /**
    Test, to check if tab layout movie click would show right data
     */

    @Test
    fun `data movie would show when tab movie selected, return true`() {
        testViewModel.setData(0)
        assertThat(testViewModel.currTab.getOrAwaitValueTest()).isEqualTo(FakeData.movie())
    }

    /**
    Test, to check if tab layout tv click would show right data
     */

    @Test
    fun `data tv would show when tab tv selected, return true`() {
        testViewModel.setData(1)
        assertThat(testViewModel.currTab.getOrAwaitValueTest()).isEqualTo(FakeData.tv())
    }

    /**
    Test, to check if tab layout movie click wouldn't show data tv
     */

    @Test
    fun `data tv wouldn't show when tab movie selected, return false`() {
        testViewModel.setData(0)
        assertThat(testViewModel.currTab.getOrAwaitValueTest()).isNotEqualTo(FakeData.tv())
    }
}