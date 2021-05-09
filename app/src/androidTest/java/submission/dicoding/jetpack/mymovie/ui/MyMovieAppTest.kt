package submission.dicoding.jetpack.mymovie.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import submission.dicoding.jetpack.mymovie.R
import submission.dicoding.jetpack.mymovie.ui.adapters.ListAdapter
import submission.dicoding.jetpack.mymovie.util.IdlingResource

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
@HiltAndroidTest
class MyMovieAppTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var activityScenario: ActivityScenario<MovieActivity>

    @Before
    fun setUp() {
        hiltRule.inject()
        activityScenario = ActivityScenario.launch(MovieActivity::class.java)
        IdlingRegistry.getInstance().register(IdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        activityScenario.close()
        IdlingRegistry.getInstance().unregister(IdlingResource.espressoTestIdlingResource)
    }

    /* Main Fragment Test */

    /**
    Test, check if all view in main fragment displayed
     */
    @Test
    fun loadAllViewInMainFragment() {
        onView(withId(R.id.tab))
            .check(matches(isDisplayed()))
        onView(withId(R.id.viewpager))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvTitleFragment))
            .check(matches(isDisplayed()))
    }

    /**
    Test, to check if viewpager be able to swipe
     */

    @Test
    fun swipeViewPager() {
        onView(withId(R.id.viewpager)).perform(swipeLeft())
        onView(withId(R.id.viewpager)).perform(swipeRight())
    }


    /**
    Test, to check if swipe layout refresh be able to swipe
     */

    @Test
    fun swipeLayoutRefresh() {
        onView(withId(R.id.layoutRefresh)).perform(swipeDown())
    }


    /* List Fragment Test */

    /**
    Test, to check if recycler and android swipe refresh view displayed
     */

    @Test
    fun loadAllViews_in_ListFragment() {
        onView(withId(R.id.rvList))
            .check(matches(isDisplayed()))

        onView(withId(R.id.layoutRefresh)).check(matches(isDisplayed()))
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
        onView(withId(R.id.layoutRefresh)).check(matches(isDisplayed()))
        onView(withId(R.id.ivPoster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvDate))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.layoutRefresh)).perform(swipeDown())

        pressBack()

        // verify if already back to main fragment
        onView(withId(R.id.rvList)).check(matches(isDisplayed()))
    }

}