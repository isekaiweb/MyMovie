package submission.dicoding.jetpack.mymovie.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import submission.dicoding.jetpack.mymovie.core.data.MyMovieRepo
import submission.dicoding.jetpack.mymovie.core.domain.repo.IMyMovieRepo

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(myMovieRepo: MyMovieRepo): IMyMovieRepo
}