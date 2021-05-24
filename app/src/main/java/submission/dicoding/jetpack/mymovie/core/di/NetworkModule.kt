package submission.dicoding.jetpack.mymovie.core.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import submission.dicoding.jetpack.mymovie.BuildConfig
import submission.dicoding.jetpack.mymovie.core.data.source.remote.network.ApiServices
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
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
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideMovieAPI(retrofit: Retrofit): ApiServices =
        retrofit.create(ApiServices::class.java)
}