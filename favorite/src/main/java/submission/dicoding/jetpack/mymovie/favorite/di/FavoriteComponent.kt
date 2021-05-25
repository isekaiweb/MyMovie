package submission.dicoding.jetpack.mymovie.favorite.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import submission.dicoding.jetpack.mymovie.di.FavoriteModuleDependencies
import submission.dicoding.jetpack.mymovie.favorite.ui.FavoriteFragment

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {
    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}