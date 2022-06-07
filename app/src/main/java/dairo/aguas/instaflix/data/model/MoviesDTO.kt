package dairo.aguas.instaflix.data.model

import com.google.gson.annotations.SerializedName
import dairo.aguas.instaflix.domain.model.Movie
import dairo.aguas.instaflix.domain.model.Movies

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
data class MoviesDTO(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<MovieDTO>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
) {
    fun toDomainMovies(): Movies {
        return Movies(
            page = page,
            totalPages = totalPages,
            totalResults = totalResults,
            movies = movies.map {
                Movie(
                    id = it.id,
                    adult = it.adult,
                    backdropPath = it.backdropPath ?: "",
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath ?: "",
                    releaseDate = it.releaseDate ?: "",
                    title = it.title,
                    video = it.video,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount
                )
            }
        )
    }
}
