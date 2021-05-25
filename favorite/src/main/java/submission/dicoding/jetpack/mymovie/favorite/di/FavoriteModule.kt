package submission.dicoding.jetpack.mymovie.favorite.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import submission.dicoding.jetpack.mymovie.core.domain.usecase.MyMovieUseCase

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModule {
    fun useCase(): MyMovieUseCase
}