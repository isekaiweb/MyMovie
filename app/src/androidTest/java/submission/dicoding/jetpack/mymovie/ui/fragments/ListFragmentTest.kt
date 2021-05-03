package submission.dicoding.jetpack.mymovie.ui.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import submission.dicoding.jetpack.mymovie.R
import submission.dicoding.jetpack.mymovie.launchFragmentInHiltContainer
import submission.dicoding.jetpack.mymovie.ui.adapters.ListAdapter

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ListFragmentTest {
    private lateinit var testFragment: ListFragment

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        hiltRule.inject()
        launchFragmentInHiltContainer<ListFragment> {
            testFragment = this
        }

    }

    /**
    Test, to check how many data in section movie
    the data size should be 10 item
     */

    @Test
    fun loadMovie_checkDataSize() {
        testFragment.viewModel.setData(0)
        val size = testFragment.listAdapter.movie.size
        onView(withId(R.id.rvList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvList)).perform(
            RecyclerViewActions.scrollToPosition<ListAdapter.ListViewHolder>(
                size
            )
        )
        assertThat(size).isEqualTo(10)
    }


    /**
    Test, to check how many data in section tv
    the data size should be 10 item
     */

    @Test
    fun loadTv() {
        testFragment.viewModel.setData(1)
        val size = testFragment.listAdapter.movie.size
        onView(withId(R.id.rvList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvList)).perform(
            RecyclerViewActions.scrollToPosition<ListAdapter.ListViewHolder>(
                size
            )
        )
        assertThat(size).isEqualTo(10)
    }


}