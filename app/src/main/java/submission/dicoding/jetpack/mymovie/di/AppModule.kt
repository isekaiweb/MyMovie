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
import submission.dicoding.jetpack.mymovie.core.data.MyMovieRepo
import submission.dicoding.jetpack.mymovie.core.domain.usecase.MyMovieInteractor
import submission.dicoding.jetpack.mymovie.core.domain.usecase.MyMovieUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context): SharedPreferences =
        app.getSharedPreferences("save_search", Context.MODE_PRIVATE)


    @Provides
    @Singleton
    fun provideUseCase(repo: MyMovieRepo): MyMovieUseCase = MyMovieInteractor(repo)

}