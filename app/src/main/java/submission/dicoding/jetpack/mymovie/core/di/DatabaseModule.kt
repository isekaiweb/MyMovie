package submission.dicoding.jetpack.mymovie.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import submission.dicoding.jetpack.mymovie.core.data.source.local.db.MyMovieDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MyMovieDatabase::class.java,
        "MyMovie.db"
    ).build()


    @Provides
    @Singleton
    fun provideFavoriteDataSource(
        database: MyMovieDatabase
    ) = database.favoriteDao()
}