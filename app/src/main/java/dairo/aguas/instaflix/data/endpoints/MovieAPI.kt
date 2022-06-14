package dairo.aguas.instaflix.data.endpoints

import dairo.aguas.instaflix.data.model.MovieDTO
import dairo.aguas.instaflix.data.model.MoviesDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
interface MovieAPI {

    @GET(MOVIES_POPULAR_END_POINT)
    suspend fun getMoviesPopular(@Query("api_key") apiKey: String): MoviesDTO

    @GET(MOVIE_DETAIL_END_POINT)
    suspend fun getMovieDetail(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): MovieDTO
}

private const val MOVIES_POPULAR_END_POINT = "movie/popular"
private const val MOVIE_DETAIL_END_POINT = "movie/{movie_id}"
