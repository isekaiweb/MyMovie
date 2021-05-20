package submission.dicoding.jetpack.mymovie.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import submission.dicoding.jetpack.mymovie.BuildConfig
import submission.dicoding.jetpack.mymovie.R
import submission.dicoding.jetpack.mymovie.api.MyMovieServices
import submission.dicoding.jetpack.mymovie.db.FavoriteDao
import submission.dicoding.jetpack.mymovie.db.MovieDatabase
import submission.dicoding.jetpack.mymovie.repo.BaseFavoriteRepo
import submission.dicoding.jetpack.mymovie.repo.FavoriteRepo
import submission.dicoding.jetpack.mymovie.util.Constants.BASE_URL
import submission.dicoding.jetpack.mymovie.util.Constants.DATABASE_NAME
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

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(logging)
        }
        return httpClient.build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideMovieAPI(retrofit: Retrofit): MyMovieServices =
        retrofit.create(MyMovieServices::class.java)

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideFavoriteRepo(
        dao: FavoriteDao
    ) = FavoriteRepo(dao) as BaseFavoriteRepo


    @Provides
    @Singleton
    fun provideFavoriteDataSource(
        database: MovieDatabase
    ) = database.favoriteDao()


    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context): SharedPreferences =
        app.getSharedPreferences("save_search", Context.MODE_PRIVATE)
}