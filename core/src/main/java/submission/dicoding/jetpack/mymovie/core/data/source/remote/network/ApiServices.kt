package submission.dicoding.jetpack.mymovie.core.data.source.remote.network


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import submission.dicoding.jetpack.mymovie.core.BuildConfig.API_KEY
import submission.dicoding.jetpack.mymovie.core.data.source.remote.response.AllResponse
import submission.dicoding.jetpack.mymovie.core.data.source.remote.response.ListAllResponse

interface ApiServices {
    @GET("{media_type}/{category}?api_key=$API_KEY")
    suspend fun getAllList(
        @Path("media_type") mediaType: String,
        @Path("category") category: String,
        @Query("page") page: Int,
    ): ListAllResponse

    @GET("search/multi?api_key=$API_KEY")
    suspend fun searchAllType(
        @Query("page") page: Int,
        @Query("query") query: String?
    ): ListAllResponse

    @GET("{media_type}/{media_id}?api_key=$API_KEY")
    suspend fun getItemDetail(
        @Path("media_type") mediaType: String,
        @Path("media_id") mediaId: Int,
    ):AllResponse
}