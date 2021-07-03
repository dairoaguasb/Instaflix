package dairo.aguas.instaflix.domain.model

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
data class Movies(
    val page: Int,
    val movies: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)