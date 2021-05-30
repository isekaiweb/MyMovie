package submission.dicoding.jetpack.mymovie.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import submission.dicoding.jetpack.mymovie.core.domain.model.AllData
import submission.dicoding.jetpack.mymovie.core.util.DataMapper.allDataToFavorite
import submission.dicoding.jetpack.mymovie.core.util.Resource
import submission.dicoding.jetpack.mymovie.usecase.MyMovieInteractorTest
import submission.dicoding.jetpack.mymovie.util.FakeData
import submission.dicoding.jetpack.mymovie.util.MainCoroutinesRule
import submission.dicoding.jetpack.mymovie.util.getOrAwaitValue

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi

class DetailViewModelTest {
    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailViewModel

    @Mock
    private lateinit var observeResourceDetail: Observer<Resource<AllData>>

    @Before
    fun setup() {
        viewModel = DetailViewModel(MyMovieInteractorTest())
    }

    @Test
    fun `should return observer resource data from allData`() {
        val value = viewModel.getDetailItem("movie", 1)

        value.observeForever(observeResourceDetail)
        observeResourceDetail.onChanged(value.getOrAwaitValue())

        assertThat(value.getOrAwaitValue().data).isEqualTo(FakeData.createAllData)
    }

    @Test
    fun `should return observer resource error`() {
        val value = viewModel.getDetailItem("movie", 2)

        value.observeForever(observeResourceDetail)
        observeResourceDetail.onChanged(value.getOrAwaitValue())

        assertThat(value.getOrAwaitValue().message).isEqualTo("data not found")
    }

    @Test
    fun `should insert data to favorite`() = runBlockingTest {
        val newData = FakeData.createAllData
        viewModel.insertFavorite(allDataToFavorite(FakeData.createAllData))

        val value = viewModel.isFavorite(newData.id)
        assertThat(value.getOrAwaitValue()).isEqualTo(1)
    }

    @Test
    fun `should remove data from favorite`() = runBlockingTest {
        val newData = FakeData.createAllData
        viewModel.insertFavorite(allDataToFavorite(FakeData.createAllData))

        val valueBefore = viewModel.isFavorite(newData.id)
        assertThat(valueBefore.getOrAwaitValue()).isEqualTo(1)

        viewModel.deleteFavorite(allDataToFavorite(newData))

        val valueAfter = viewModel.isFavorite(newData.id)
        assertThat(valueAfter.getOrAwaitValue()).isEqualTo(0)
    }

}