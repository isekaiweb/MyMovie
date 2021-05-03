package submission.dicoding.jetpack.mymovie.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import submission.dicoding.jetpack.mymovie.R
import submission.dicoding.jetpack.mymovie.models.MovieResponse
import submission.dicoding.jetpack.mymovie.repo.DefaultRepo
import submission.dicoding.jetpack.mymovie.repo.MovieRepo
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ): RequestManager = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .centerCrop()
    )

    @Singleton
    @Provides
    @Named("movie")
    fun provideDataMovieShow(): List<MovieResponse> =
        listOf(
            MovieResponse(
                1,
                "Mortal Kombat",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6Wdl9N6dL0Hi0T1qJLWSz6gMLbd.jpg",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe",
                "04 April 2021"
            ),
            MovieResponse(
                2,
                "Godzilla vs. Kong",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "24 March 2021"
            ),
            MovieResponse(
                3,
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                "Tanjiro Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyojuro Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "16 October 2020"

            ),
            MovieResponse(
                4,
                "Nobody",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "28 April 2021"
            ),
            MovieResponse(
                5,
                "The Unholy",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "31 March 2021"
            ), MovieResponse(
                6,
                "Zack Snyder's Justice League",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                "18 March 2021"
            ), MovieResponse(
                7,
                "The Marksman",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6vcDalR50RWa309vBH1NLmG2rjQ.jpg",
                "Jim Hanson’s quiet life is suddenly disturbed by two people crossing the US/Mexico border – a woman and her young son – desperate to flee a Mexican cartel. After a shootout leaves the mother dead, Jim becomes the boy’s reluctant defender. He embraces his role as Miguel’s protector and will stop at nothing to get him to safety, as they go on the run from the relentless assassins",
                "15 January 2021"
            ), MovieResponse(
                8,
                "Miraculous World: Shanghai – The Legend of Ladydragon",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/msI5a9TPnepx47JUb2vl88hb80R.jpg",
                "To join Adrien in Shanghai, Marinette is going to visit her uncle Wang who is celebrating his anniversary. But, as soon as she arrives in China, her purse gets stolen with Tikki inside, whom she needs to secretly transform into Ladybug! Without money and alone in the immense city, Marinette accepts the help of a young and resourceful girl, Fei. The two girls will ally and discover the existence of a new magical jewel, the Prodigious. Hawk Moth, also present in Shanghai, seeks to finding it since a long time...",
                "04 April 2021"
            ),
            MovieResponse(
                9,
                "Vanquish",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
                "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
                "16 April 2021"
            ),
            MovieResponse(
                10,
                "Thunder Force",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/duK11VQd4UPDa7UJrgrGx90xJOx.jpg",
                "In a world where supervillains are commonplace, two estranged childhood best friends reunite after one devises a treatment that gives them powers to protect their city.",
                "09 April 2021"
            )
        )

    @Singleton
    @Provides
    @Named("tv")
    fun provideDataTvShow(): List<MovieResponse> = listOf(
        MovieResponse(
            11,
            "The Falcon and the Winter Soldier",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            "19 March 2021"
        ),

        MovieResponse(
            12,
            "The Good Doctor",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
            "25 September 2017"
        ),
        MovieResponse(
            13,
            "The Flash",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "07 October 2014"
        ),
        MovieResponse(
            14,
            "Invincible",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
            "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
            "26 March 2021"
        ),
        MovieResponse(
            15,
            "Luis Miguel: The Series",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg",
            "The series dramatizes the life story of Mexican superstar singer Luis Miguel, who has captivated audiences in Latin America and beyond for decades.",
            "22 April 2018"
        ),
        MovieResponse(
            16,
            "Riverdale",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            "26 January 20217"
        ),
        MovieResponse(
            17,
            "Haunted: Latin America",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/Q1ZYG3kDS8iVIHOYOJ9NQmV0q7.jpg",
            "Real people's terrifying tales of the chilling, unexplained and paranormal come to life with dramatic reenactments in this reality series.",
            "31 March 2021"
        ),
        MovieResponse(
            18,
            "Grey's Anatomy",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
            "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            "27 March 2005"
        ),
        MovieResponse(
            19,
            "Lucifer",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
            "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
            "25 January 2016"
        ),
        MovieResponse(
            20,
            "The Queen of Flow",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3T5mSuziI0TMa7z9R5w3vNl2qok.jpg",
            "After spending seventeen years in prison unfairly, a talented songwriter seeks revenge on the men who sank her and killed her family.",
            "18 June 2018"
        ),
    )

    @Singleton
    @Provides
    fun provideDefaultRepo(
        @Named("movie") movie: List<MovieResponse>,
        @Named("tv") tv: List<MovieResponse>
    ) = DefaultRepo(movie, tv) as MovieRepo
}