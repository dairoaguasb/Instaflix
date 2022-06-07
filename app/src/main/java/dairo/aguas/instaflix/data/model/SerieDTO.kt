package dairo.aguas.instaflix.data.model

import com.google.gson.annotations.SerializedName
import dairo.aguas.instaflix.domain.model.Serie

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
data class SerieDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("name") val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
) {
    fun toDomainSerie(): Serie {
        return Serie(
            id = id,
            backdropPath = backdropPath ?: "",
            originalLanguage = originalLanguage,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath ?: "",
            title = title,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }
}
