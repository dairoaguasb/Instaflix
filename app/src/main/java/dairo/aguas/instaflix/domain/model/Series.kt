package dairo.aguas.instaflix.domain.model

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
data class Series(
    val page: Int,
    val series: List<Serie>,
    val totalPages: Int,
    val totalResults: Int
)
