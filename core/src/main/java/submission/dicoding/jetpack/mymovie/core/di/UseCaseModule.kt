package submission.dicoding.jetpack.mymovie.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import submission.dicoding.jetpack.mymovie.core.domain.usecase.MyMovieInteractor
import submission.dicoding.jetpack.mymovie.core.domain.usecase.MyMovieUseCase

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    @ViewModelScoped
    abstract fun provideTourismUseCase(interactor: MyMovieInteractor): MyMovieUseCase
}