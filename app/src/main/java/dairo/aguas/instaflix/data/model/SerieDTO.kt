package dairo.aguas.instaflix.data.model

import com.squareup.moshi.Json

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
data class SerieDTO(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "backdrop_path") val backdropPath: String?,
    @field:Json(name = "original_language") val originalLanguage: String,
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "poster_path") val posterPath: String?,
    @field:Json(name = "name") val title: String,
    @field:Json(name = "vote_average") val voteAverage: Double,
    @field:Json(name = "vote_count") val voteCount: Int
)