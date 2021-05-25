package submission.dicoding.jetpack.mymovie.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import submission.dicoding.jetpack.mymovie.core.data.MyMovieRepo
import submission.dicoding.jetpack.mymovie.core.data.source.local.LocalDataSource
import submission.dicoding.jetpack.mymovie.core.data.source.remote.PagingSource
import submission.dicoding.jetpack.mymovie.core.data.source.remote.RemoteDataSource
import submission.dicoding.jetpack.mymovie.core.data.source.remote.network.ApiServices
import submission.dicoding.jetpack.mymovie.core.domain.repo.IMyMovieRepo

@Module
@InstallIn(ActivityComponent::class)
object RepositoryModule {

    @Provides
    @ActivityScoped
    fun provideRemoteDataSource(service: ApiServices): RemoteDataSource = RemoteDataSource(service)

    @Provides
    @ActivityScoped
    fun providePagingSource(service: ApiServices): PagingSource = PagingSource(service)

    @Provides
    @ActivityScoped
    fun provideRepo(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource)
            : IMyMovieRepo = MyMovieRepo(remoteDataSource, localDataSource)
}