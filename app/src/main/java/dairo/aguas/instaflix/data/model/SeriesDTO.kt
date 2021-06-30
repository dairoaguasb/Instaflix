package dairo.aguas.instaflix.data.model

import com.squareup.moshi.Json

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
data class SeriesDTO(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val series: List<SerieDTO>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)
