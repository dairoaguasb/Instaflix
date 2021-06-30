package dairo.aguas.instaflix.data.model

import com.squareup.moshi.Json
import dairo.aguas.instaflix.domain.model.Serie
import dairo.aguas.instaflix.domain.model.Series

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
data class SeriesDTO(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val series: List<SerieDTO>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
) {
    fun toDomainSeries(): Series {
        return Series(
            page = page,
            totalPages = totalPages,
            totalResults = totalResults,
            series = series.map {
                Serie(
                    id = it.id,
                    backdropPath = it.backdropPath ?: "",
                    originalLanguage = it.originalLanguage,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath ?: "",
                    title = it.title,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount
                )
            }
        )
    }
}
