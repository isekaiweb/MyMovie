package submission.dicoding.jetpack.mymovie.favorite.util

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import submission.dicoding.jetpack.mymovie.favorite.di.FavoriteModule
import submission.dicoding.jetpack.mymovie.favorite.ui.ListFavoriteFragment

@Component(dependencies = [FavoriteModule::class])
interface FavoriteComponent {
    fun inject(fragment: ListFavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModule: FavoriteModule): Builder
        fun build(): FavoriteComponent
    }
}