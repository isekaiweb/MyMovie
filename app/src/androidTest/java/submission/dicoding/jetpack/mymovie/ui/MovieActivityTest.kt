package submission.dicoding.jetpack.mymovie.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import submission.dicoding.jetpack.mymovie.R
import submission.dicoding.jetpack.mymovie.ui.adapters.ListAdapter

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class MovieActivityTest {

    private lateinit var activityScenario: ActivityScenario<MovieActivity>


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        hiltRule.inject()
        activityScenario = ActivityScenario.launch(MovieActivity::class.java)
    }


    @After
    fun teardown() {
        activityScenario.close()
    }

    /* Main Fragment Test */

    /**
    Test, check if all view in main fragment displayed
     */
    @Test
    fun loadAllViewInMainFragment() {
        onView(withId(R.id.tab)).check(matches(isDisplayed()))
        onView(withId(R.id.viewpager)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitleFragment)).check(matches(isDisplayed()))
    }

    /**
    Test, to check if viewpager be able to swipe
     */

    @Test
    fun swipeViewPager() {
        onView(withId(R.id.viewpager)).perform(swipeLeft())
        onView(withId(R.id.viewpager)).perform(swipeRight())
    }


    /* List Fragment Test */

    /**
    Test, to check if recycler view displayed
     */

    @Test
    fun loadRecyclerView() {
        onView(withId(R.id.rvList)).check(matches(isDisplayed()))
    }

    /**
    Test, to check if item in recycler view in list fragment on section movie when click would navigate to detailFragment
     */
    @Test
    fun clickItemMovie_NavigateToDetailFragment() {
        onView(withId(R.id.rvList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ListAdapter.ListViewHolder>(
                5,
                click()
            )
        )
    }

    /**
    Test, to check if item in recycler view in list fragment on section tv when click would navigate to detailFragment
     */
    @Test
    fun clickItemTv_NavigateToDetailFragment() {
        onView(withId(R.id.viewpager)).perform(swipeLeft())
        onView(withId(R.id.rvList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ListAdapter.ListViewHolder>(
                2,
                click()
            )
        )
    }

    /* Detail Fragment Test */
    /**
    Test, to check if all view in DetailFragment displayed and when back navigation click would back to main fragment
     */
    @Test
    fun loadAllViewInDetailFragment_pressBack() {
        onView(withId(R.id.rvList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ListAdapter.ListViewHolder>(
                2,
                click()
            )
        )
        onView(withId(R.id.ivPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))

        pressBack()
    }

}