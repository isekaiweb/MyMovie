package submission.dicoding.jetpack.mymovie.ui.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
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
import submission.dicoding.jetpack.mymovie.ui.fragments.ListFragment.Companion.PAGE_TYPE


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class MainFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        hiltRule.inject()
        launchFragmentInHiltContainer<MainFragment>()
    }

    /**
    Test, to check if viewpager swiped to left, the argument in listFragment
    should be 1 (it's mean data would displayed is data tv)
     */

    @Test
    fun swipeViewPagerToTheLeft_and_GetTypeMovie_1() {
        onView(withId(R.id.viewpager)).perform(swipeLeft())
        launchFragmentInHiltContainer<ListFragment> {
            this.arguments?.getInt(PAGE_TYPE)?.let { page ->
                assertThat(page).isEqualTo(1)
            }
        }
    }

    /**
    Test, to check if viewpager swiped to left, the argument in listFragment
    should be 0  (it's mean data would displayed is data movie)
     */

    @Test
    fun swipeViewPagerToTheRight_and_GetTypeMovie_0() {
        onView(withId(R.id.viewpager)).perform(swipeLeft())
        onView(withId(R.id.viewpager)).perform(swipeRight())
        launchFragmentInHiltContainer<ListFragment> {
            this.arguments?.getInt(PAGE_TYPE)?.let { page ->
                assertThat(page).isEqualTo(0)
            }
        }
    }
}