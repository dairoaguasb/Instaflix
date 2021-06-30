package dairo.aguas.instaflix.domain.model

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
data class Serie(
    val id: Int,
    val backdropPath: String,
    val originalLanguage: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)