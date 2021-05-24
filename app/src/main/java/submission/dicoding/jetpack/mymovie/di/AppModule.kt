package submission.dicoding.jetpack.mymovie.di

import android.content.Context
import android.content.SharedPreferences
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import submission.dicoding.jetpack.mymovie.R
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ): RequestManager = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .centerCrop()
    )

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context): SharedPreferences =
        app.getSharedPreferences("save_search", Context.MODE_PRIVATE)

}