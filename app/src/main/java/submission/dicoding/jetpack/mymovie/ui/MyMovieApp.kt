package submission.dicoding.jetpack.mymovie.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltAndroidApp
class MyMovieApp : Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)
    override fun onCreate() {
        super.onCreate()
        applicationScope.launch {
            Timber.plant(Timber.DebugTree())
        }
    }
}