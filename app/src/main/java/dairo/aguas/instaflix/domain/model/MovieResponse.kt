package dairo.aguas.instaflix.domain.model

import com.squareup.moshi.Json

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
data class MovieResponse(
    val page: Int,
    @field:Json(name = "results") val movieList: List<Movie>,
    @field:Json(name = "total_pages") val totalPages: Int,
    @field:Json(name = "total_results") val totalResults: Int
)