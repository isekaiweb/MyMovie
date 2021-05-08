package submission.dicoding.jetpack.mymovie.utils

import submission.dicoding.jetpack.mymovie.models.Movie

internal object FakeData {
    fun createMovie(): List<Movie> =
        listOf(
            Movie(
                id = 1,
                name = "Mortal Kombat",
                poster_path = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                overview = "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe",
                release_date = "04 April 2021"
            ),

            )
}