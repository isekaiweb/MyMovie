package submission.dicoding.jetpack.mymovie.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
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
import submission.dicoding.jetpack.mymovie.ui.adapters.FavoriteAdapter
import submission.dicoding.jetpack.mymovie.ui.adapters.ListAdapter
import submission.dicoding.jetpack.mymovie.util.IdlingResource

/** WARNING !
 * all testing would able successful if nothing saved in favorite
 **/

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
@HiltAndroidTest
class MyMovieEntityAppTest {

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
        onView(withId(R.id.tvTitleMain))
            .check(matches(isDisplayed()))
        onView(withId(R.id.ibSearch))
            .check(matches(isDisplayed()))
        onView(withId(R.id.ibBookmark))
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
    Test, to check if tab be able to click
     */
    @Test
    fun tabLayout() {
        onView(withText("Series  \uD83D\uDCFA")).perform(click())
        onView(withText("Movies  \uD83C\uDFAC")).perform(click())
    }


    /* List Fragment Test */

    /**
    Test, to check if recycler view displayed
     */

    @Test
    fun loadAllViews_in_ListFragment() {
        onView(withId(R.id.rvList))
            .check(matches(isDisplayed()))
    }


    /**
    Test, to check if swipe layout refresh be able to swipe and show up
     */

    @Test
    fun swipeLayoutRefresh() {
        onView(withId(R.id.layoutRefresh)).perform(swipeDown())
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

        onView(withId(R.id.ivPoster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvDate))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview))
            .check(matches(isDisplayed()))
        onView(withId(R.id.layoutRefresh)).perform(swipeDown())
        onView(withId(R.id.layoutRefresh)).check(matches(isDisplayed()))
        onView(withId(R.id.tbFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.shade)).check(matches(isDisplayed()))

        pressBack()

        // verify if already back to main fragment
        onView(withId(R.id.rvList)).check(matches(isDisplayed()))
    }

    /**
     *Test, to check if bookmark be able to click
     * */
    @Test
    fun clickBookMarkIcon() {
        val pos = 5
        onView(withId(R.id.rvList)).perform(
            RecyclerViewActions.scrollToPosition<ListAdapter.ListViewHolder>(
                pos
            )
        )
        onView(withId(R.id.rvList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ListAdapter.ListViewHolder>(
                pos,
                click()
            )
        )
        onView(withId(R.id.tbFavorite)).check(matches(isClickable()))
    }

    /* Favorite Fragment */

    /**
     * Test, to check all view in favorite fragment displayed
     * */
    @Test
    fun loadAllViewInFavoriteFragment() {
        onView(withId(R.id.ibBookmark)).perform(click())
        onView(withId(R.id.ibBack))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tvTitleFavorite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rvFavorite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tab))
            .check(matches(isDisplayed()))
        onView(withId(R.id.viewpager))
    }

    /**
    Test, to check if viewpager be able to swipe
     */

    @Test
    fun swipeViewPageFavorite() {
        onView(withId(R.id.viewpager)).perform(swipeLeft())
        onView(withId(R.id.viewpager)).perform(swipeRight())
    }

    /**
    Test, to check if tab be able to click
     */
    @Test
    fun tabLayoutFavorite() {
        onView(withText("Series  \uD83D\uDCFA")).perform(click())
        onView(withText("Movies  \uD83C\uDFAC")).perform(click())
    }


    /**
     * Test, to check tv be able to set as favorite
     * */
    @Test
    fun setTvToFavoriteAndVerify() {
        //save tv first
        onView(withText("Series  \uD83D\uDCFA")).perform(click())
        onView(withId(R.id.rvList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ListAdapter.ListViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tbFavorite)).perform(click())
        //back to main
        pressBack()

        //doing main test to see if rvFavorite running well
        onView(withId(R.id.ibBookmark)).perform(click())
        onView(withText("Series  \uD83D\uDCFA")).perform(click())
        onView(withId(R.id.rvFavorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<FavoriteAdapter.FavoriteViewHolder>(
                0,
                click()
            )
        )

        //remove tv from database
        onView(withId(R.id.tbFavorite)).perform(click())
        pressBack()
        //verify tv favorite is empty now
        onView(withId(R.id.ivEmpty)).check(matches(isDisplayed()))
    }

    /**
     * Test, to if item in recycle view favorite be able to click and navigate to detail,
     * and back to main fragment
     * */
    @Test
    fun clickItemInFavorite_navigateBack_to_MainFragment() {
        //save movie first
        onView(withId(R.id.rvList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ListAdapter.ListViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tbFavorite)).perform(click())
        //back to main
        pressBack()

        //doing main test to see if rvFavorite running well
        onView(withId(R.id.ibBookmark)).perform(click())
        onView(withId(R.id.rvFavorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<FavoriteAdapter.FavoriteViewHolder>(
                0,
                click()
            )
        )

        //remove movie from database
        onView(withId(R.id.tbFavorite)).perform(click())

        pressBack()
        onView(withId(R.id.ivEmpty)).check(matches(isDisplayed()))
        onView(withId(R.id.ibBack)).perform(click())
        // verify if already back to main fragment
        onView(withId(R.id.tvTitleMain)).check(matches(isDisplayed()))
    }

    /* Search Fragment */

    /**
     * Test, to check all view in search fragment displayed
     * */
    @Test
    fun loadAllViewInSearchFragment() {
        onView(withId(R.id.ibSearch)).perform(click())
        onView(withId(R.id.ibBack))
            .check(matches(isDisplayed()))
        onView(withId(R.id.etSearch))
            .check(matches(isDisplayed()))
        onView(withId(R.id.ivSearch))
            .check(matches(isDisplayed()))
        onView(withId(R.id.layoutRefresh))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rvSearch))
            .check(matches(isDisplayed()))
    }


    /**
     * Test, to type in search field and then click one item and check if navigate well to detail fragment
     * and then press back to search fragment.
     * check if layout refresh be able to swipe down
     * check if button back can navigate to main fragment properly
     * */
    @Test
    fun typeInSearchText_and_clickItem_thenBackToMainFragment() {
        onView(withId(R.id.ibSearch)).perform(click())
        onView(withId(R.id.etSearch)).perform(clearText())
        onView(withId(R.id.etSearch)).perform(typeText("avengers"))
        onView(withId(R.id.ivSearch)).perform(click())

        onView(withId(R.id.rvSearch)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ListAdapter.ListViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.ivPoster)).check(matches(isDisplayed()))
        pressBack()

        onView(withId(R.id.etSearch))
            .check(matches(isDisplayed()))
        onView(withId(R.id.layoutRefresh)).perform(swipeDown())

        onView(withId(R.id.ibBack)).perform(click())
        onView(withId(R.id.rvList)).check(matches(isDisplayed()))
    }

}