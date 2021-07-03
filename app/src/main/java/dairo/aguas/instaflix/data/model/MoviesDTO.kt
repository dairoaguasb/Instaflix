package dairo.aguas.instaflix.data.model

import com.squareup.moshi.Json
import dairo.aguas.instaflix.domain.model.Movie
import dairo.aguas.instaflix.domain.model.Movies

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
data class MoviesDTO(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val movies: List<MovieDTO>,
    @field:Json(name = "total_pages") val totalPages: Int,
    @field:Json(name = "total_results") val totalResults: Int
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