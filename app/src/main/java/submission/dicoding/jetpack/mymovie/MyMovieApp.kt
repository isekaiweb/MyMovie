package submission.dicoding.jetpack.mymovie

import android.app.Application
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MyMovieApp : Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate() {
        super.onCreate()
        sharedPreferences.edit().putBoolean("firstTime", true).apply()
        applicationScope.launch {
            Timber.plant(Timber.DebugTree())
        }

    }
}