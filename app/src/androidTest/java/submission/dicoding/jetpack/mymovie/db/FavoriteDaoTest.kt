package submission.dicoding.jetpack.mymovie.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import submission.dicoding.jetpack.mymovie.utils.FakeData
import submission.dicoding.jetpack.mymovie.utils.getOrAwaitValue
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class FavoriteDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: MovieDatabase
    private lateinit var dao: FavoriteDao

    private var dummyData = FakeData.createFavorite

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.favoriteDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    /**
     * test, to check if movie be able to insert in database
     * */
    @Test
    @Throws(Exception::class)
    fun insertFavorite() = runBlockingTest {
        dao.insert(dummyData)
        val value = dao.countFavorite("movie").getOrAwaitValue()

        assertThat(value).isEqualTo(1)
    }

    /**
     * test, to check if movie already in favorite
     * */
    @Test
    @Throws(Exception::class)
    fun checkMovie() = runBlockingTest {
        dao.insert(dummyData)

        val value = dao.checkFavorite(dummyData.id)
        assertThat(value).isEqualTo(1)
    }

    /**
     * test, to check if movie in database be able to remove
     * */
    @Test
    @Throws(Exception::class)
    fun insertAndThenDelete() = runBlockingTest {
        dao.insert(dummyData)
        val valueBefore = dao.countFavorite("movie").getOrAwaitValue()
        assertThat(valueBefore).isEqualTo(1)

        dao.delete(dummyData)
        val valueAfter = dao.countFavorite("movie").getOrAwaitValue()
        assertThat(valueAfter).isEqualTo(0)
    }


}