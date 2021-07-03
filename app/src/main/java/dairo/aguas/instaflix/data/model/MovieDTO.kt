package dairo.aguas.instaflix.data.model

import com.squareup.moshi.Json
import dairo.aguas.instaflix.domain.model.Movie

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
data class MovieDTO(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "adult") val adult: Boolean,
    @field:Json(name = "backdrop_path") val backdropPath: String?,
    @field:Json(name = "original_language") val originalLanguage: String,
    @field:Json(name = "original_title") val originalTitle: String,
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "poster_path") val posterPath: String?,
    @field:Json(name = "release_date") val releaseDate: String?,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "video") val video: Boolean,
    @field:Json(name = "vote_average") val voteAverage: Double,
    @field:Json(name = "vote_count") val voteCount: Int
) {
    fun toDomainMovie(): Movie {
        return Movie(
            id = id,
            adult = adult,
            backdropPath = backdropPath ?: "",
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath ?: "",
            releaseDate = releaseDate ?: "",
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }
}