package dairo.aguas.instaflix.data.endpoints

import dairo.aguas.instaflix.data.model.MoviesDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
interface MovieAPI {

    @GET(MOVIES_POPULAR_END_POINT)
    fun getMoviesPopular(@Query("api_key") apiKey: String): Flow<MoviesDTO>

    @GET(MOVIES_LATEST_END_POINT)
    fun getMoviesLatest(@Query("api_key") apiKey: String): Flow<MoviesDTO>

    @GET(MOVIES_TOP_RATED_END_POINT)
    fun getMoviesTopRated(@Query("api_key") apiKey: String): Flow<MoviesDTO>
}

private const val MOVIES_POPULAR_END_POINT = "movie/popular"
private const val MOVIES_LATEST_END_POINT = "movie/latest"
private const val MOVIES_TOP_RATED_END_POINT = "movie/top_rated"