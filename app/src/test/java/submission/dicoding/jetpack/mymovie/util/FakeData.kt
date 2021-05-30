package submission.dicoding.jetpack.mymovie.util

import submission.dicoding.jetpack.mymovie.core.domain.model.AllData

object FakeData {
    val createAllData = AllData(
        id=1,
        poster_path = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
        title = "Mortal Kombat",
        media_type = "movie",
        date = "22 february 2021",
        overview = "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of out world in a high stakes battle for the universe"
    )
}