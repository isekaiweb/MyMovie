# Submission 2 Dicoding Academy Android Jetpack Pro
This is an app i created for accomplish task from [Dicoding](https://www.dicoding.com/) on subject [BAJP](https://www.dicoding.com/academies/129) and i got 5 star of 5 you can see my certificate [here](https://www.dicoding.com/certificates/ERZR5V5QOPYV), by the way if you found my project because you wanted to accomplish your final task from **Dicoding** also please use this as reference do not copy all of the code or it'll be counted as **Plagiarism**

### Another submission you can see here 
- [submission 3](https://github.com/isekaiweb/MyMovie/tree/submission_3)
- [submission 1](https://github.com/isekaiweb/MyMovie/tree/submission_1)

## Attention ⚠
i'm using TMDb API here hence you need to create your own KEY API first to running this code properly, you can visit [here](https://www.themoviedb.org/login) to create your account first and go to settings click to API [here](https://www.themoviedb.org/settings/api)

### After you get KEY API, you must create one more thing
click on your file, select new file and named **gradle.properties**
and the add this all code inside the file :
```
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
android.useAndroidX=true
android.enableJetifier=true
kotlin.code.style=official
API_KEY = "" -> (place your KEY API here)
```
## End Point
- Movie Now Playing : [/movie/now_playing](https://developers.themoviedb.org/3/movies/get-now-playing)
- Detail Movie : [/movie/{movie_id}](https://developers.themoviedb.org/3/movies/get-movie-details)
- TV on Airing : [/tv/on_the_air](https://developers.themoviedb.org/3/tv/get-tv-on-the-air)
- Detail TV : [/tv/{tv_id}](https://developers.themoviedb.org/3/tv/get-tv-details)
- Search : [/search/multi](https://developers.themoviedb.org/3/search/multi-search)