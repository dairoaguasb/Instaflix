package dairo.aguas.instaflix.data.model

import com.google.gson.annotations.SerializedName
import dairo.aguas.instaflix.domain.model.Movie

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
data class MovieDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
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
