# Final Submission Dicoding Academy Become Android Developer Expert [![isekaiweb](https://circleci.com/gh/isekaiweb/MyMovie.svg?style=svg)](https://app.circleci.com/pipelines/github/isekaiweb/MyMovie)
This is an app i created for accomplish task from [Dicoding](https://www.dicoding.com/) on subject [MADE](https://www.dicoding.com/academies/165) and i got 4 star of 5 you can see my certificate [here](https://www.dicoding.com/certificates/1OP8DK50LPQK), by the way if you found my project because you wanted to accomplish your final task from **Dicoding** also please use this as reference do not copy all of the code or it'll be counted as **Plagiarism**

### Previous submission you can see here 
- [Final Submission](https://github.com/isekaiweb/MyMovie/tree/submission_1_MADE)

## Attention ⚠
i'm using TMDb API here hence you need to create your own KEY API first to running this code properly, you can visit [here](https://www.themoviedb.org/login) to create your account first and go to settings click to API [here](https://www.themoviedb.org/settings/api)

### After you get KEY API
- create new file secrets.properties
- in secrets.properties create variable API_KEY
- place your API_KEY -> API_KEY = "{Your API KEY}"

## End Point
- Movie Now Playing : [/movie/now_playing](https://developers.themoviedb.org/3/movies/get-now-playing)
- Detail Movie : [/movie/{movie_id}](https://developers.themoviedb.org/3/movies/get-movie-details)
- TV on Airing : [/tv/on_the_air](https://developers.themoviedb.org/3/tv/get-tv-on-the-air)
- Detail TV : [/tv/{tv_id}](https://developers.themoviedb.org/3/tv/get-tv-details)
- Search : [/search/multi](https://developers.themoviedb.org/3/search/multi-search)

## This is what i have to do to accomplish the task
**Implementing Continuous Integration**
- You are Free to use (CircleCI,Github Action,TravisCI,etc) choose one
- do a test and build APK successfully without any issue or get label pass for your CI project

**Have a Good Performance**
- Implementing *Leak Canary* without any memory leaks while analyzing
- No issue refers to performance while inspect code

**Have a Security**
- Implementing *obfuscation* with ProGuard 
- Implementing *encryption* for database
- Implementing *certificate pinning* fro connection to server

**Create 3 Main Feature**
- You Free to decide what's Themes or APIs you use
- There are must have page list item page, detail item, and list favorite (use it database)
- All Feature can work properly without any force close

**Implementing Modularization**
- Create 1 Android Library for core and 1 dynamic feature for favorite

**Implementing Clean Architecture**
- Not Violate Dependency rule and Clean Architecture
- Separating model for domain with model for data (separation model)

**Implementing Dependency Injection**
- Must use library Dependency Injection like: Dagger/Hilt/Koin/Kodein (Choose One)
- Implementing Injection correctly for all Functionality

**Implementing Reactive Programming**
- You can use Rx/Flow (Choose one)
- Implement properly to retrieve data from network and database


## Tools
- [Lifecycle & LiveData](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [Navigation Component](https://developer.android.com/jetpack/androidx/releases/navigation)
- [Dagger Hilt](https://dagger.dev/hilt/)
- [Retrofit 2](https://square.github.io/retrofit/)
- [Glide](https://github.com/bumptech/glide)
- [MVVM](https://developer.android.com/jetpack/guide)
- [ROOM](https://developer.android.com/jetpack/androidx/releases/room)
-  [Smooth Loader](https://github.com/nntuyen/mkloader)
- [View Binding](https://developer.android.com/topic/libraries/view-binding?hl=en)
- [Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
-[ViewPager2](https://developer.android.com/jetpack/androidx/releases/viewpager2?hl=id)
- [Kotlin Coroutines Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/)
- [Truth](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/)
- [Flow](https://developer.android.com/kotlin/flow)
- [Clean Architecture MVVM](https://www.toptal.com/android/android-apps-mvvm-with-clean-architecture)
- [Secrets Gradle](https://github.com/google/secrets-gradle-plugin)
- [Lottie](https://airbnb.design/lottie/)
- [Circle CI](https://app.circleci.com/)
- [LeakCanary](https://square.github.io/leakcanary/)


## Features
**Main Page**

In Main Page just to display both list Movie and Series.
i'm using ViewPager2 to split them and to make user easily can navigate between movie and series just by swiping to the left or right in here too i'm using TabLayout so user can know what's tab opened now

![Main Page](https://github.com/isekaiweb/MyMovie/blob/submission_3/demo/main.gif)


**Detail Page**

here just to display all information about the movies or series user click from main page and from this page user can mark that movie or series as favorite and can easy to find the movie/series which has mark favorite

![Detail Page](https://github.com/isekaiweb/MyMovie/blob/submission_3/demo/detail.gif)


**Favorite Page**

This page have 2 page like main page i use ViewPager2 and TabLayout to split both of them to make user can easily find their favorite movie or series

![Favorite Page](https://github.com/isekaiweb/MyMovie/blob/submission_3/demo/favorite.gif)

**Search Page**

 This the page for user to find movie or series they want to find out
 
![Search Page](https://github.com/isekaiweb/MyMovie/blob/submission_3/demo/search.gif)


