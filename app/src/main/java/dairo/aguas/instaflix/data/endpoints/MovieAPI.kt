package dairo.aguas.instaflix.data.endpoints

import dairo.aguas.instaflix.data.model.MovieDTO
import dairo.aguas.instaflix.data.model.MoviesDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
interface MovieAPI {

    @GET(MOVIES_POPULAR_END_POINT)
    fun getMoviesPopular(@Query("api_key") apiKey: String): Flow<MoviesDTO>

    @GET(MOVIES_UPCOMING_END_POINT)
    fun getMoviesUpcoming(@Query("api_key") apiKey: String): Flow<MoviesDTO>

    @GET(MOVIES_TOP_RATED_END_POINT)
    fun getMoviesTopRated(@Query("api_key") apiKey: String): Flow<MoviesDTO>

    @GET(MOVIE_DETAIL_END_POINT)
    fun getMovieDetail(@Query("api_key") apiKey: String, @Path("movie_id") id: Int): Flow<MovieDTO>
}

private const val MOVIES_POPULAR_END_POINT = "movie/popular"
private const val MOVIES_UPCOMING_END_POINT = "movie/upcoming"
private const val MOVIES_TOP_RATED_END_POINT = "movie/top_rated"
private const val MOVIE_DETAIL_END_POINT = "movie/{movie_id}"