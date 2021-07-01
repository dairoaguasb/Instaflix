package dairo.aguas.instaflix.ui.model

import dairo.aguas.instaflix.domain.model.Movie

/**
 * Created by Dairo Aguas B on 1/07/2021.
 */
data class DetailViewData(
    val id: Int,
    val overview: String,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
) {
    constructor(movie: Movie) : this(
        id = movie.id,
        overview = movie.overview,
        posterPath = IMAGE_ULR + movie.posterPath,
        title = movie.title,
        voteAverage = movie.voteAverage,
        voteCount = movie.voteCount
    )
}

private const val IMAGE_ULR = "https://image.tmdb.org/t/p/w185/"