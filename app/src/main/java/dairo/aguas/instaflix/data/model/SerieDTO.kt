package dairo.aguas.instaflix.data.model

import com.squareup.moshi.Json

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
data class SerieDTO(
    @Json(name = "id") val id: Int,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "popularity") val popularity: Double,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "name") val title: String,
    @Json(name = "vote_average") val voteAverage: Double,
    @Json(name = "vote_count") val voteCount: Int
)