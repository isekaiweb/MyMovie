package submission.dicoding.jetpack.mymovie.repo

import submission.dicoding.jetpack.mymovie.models.MovieResponse

internal object FakeData {
    fun movie(): List<MovieResponse> =
        listOf(
            MovieResponse(
                1,
                "Mortal Kombat",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe",
                "04 April 2021"
            ),

            )


    fun tv(): List<MovieResponse> = listOf(
        MovieResponse(
            11,
            "The Falcon and the Winter Soldier",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            "19 March 2021"
        ),


        )
}