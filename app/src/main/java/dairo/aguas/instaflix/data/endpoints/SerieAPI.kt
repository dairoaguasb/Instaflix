package dairo.aguas.instaflix.data.endpoints

import dairo.aguas.instaflix.data.model.SerieDTO
import dairo.aguas.instaflix.data.model.SeriesDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
interface SerieAPI {

    @GET(SERIES_POPULAR_END_POINT)
    fun getSeriesPopular(@Query("api_key") apiKey: String): Flow<SeriesDTO>

    @GET(SERIES_ON_AIR_END_POINT)
    fun getSeriesOnAir(@Query("api_key") apiKey: String): Flow<SeriesDTO>

    @GET(SERIES_TOP_RATED_END_POINT)
    fun getSeriesTopRated(@Query("api_key") apiKey: String): Flow<SeriesDTO>

    @GET(SERIE_DETAIL_END_POINT)
    fun getSerieDetail(@Path("tv_id") id: Int, @Query("api_key") apiKey: String): Flow<SerieDTO>
}

private const val SERIES_POPULAR_END_POINT = "tv/popular"
private const val SERIES_ON_AIR_END_POINT = "tv/on_the_air"
private const val SERIES_TOP_RATED_END_POINT = "tv/top_rated"
private const val SERIE_DETAIL_END_POINT = "tv/{tv_id}"