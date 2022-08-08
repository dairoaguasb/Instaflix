package dairo.aguas.instaflix.ui.model

import dairo.aguas.instaflix.domain.model.Movie
import dairo.aguas.instaflix.domain.model.Serie

/**
 * Created by Dairo Aguas B on 1/07/2021.
 */
data class DetailViewData(
    val id: Int,
    val overview: String,
    val backdropPath: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val releaseDate: String
) {
    constructor(movie: Movie) : this(
        id = movie.id,
        overview = movie.overview,
        backdropPath = IMAGE_ULR + movie.backdropPath,
        title = movie.title,
        voteAverage = movie.voteAverage,
        voteCount = movie.voteCount,
        releaseDate = movie.releaseDate
    )

    constructor(serie: Serie) : this(
        id = serie.id,
        overview = serie.overview,
        backdropPath = IMAGE_ULR + serie.backdropPath,
        title = serie.title,
        voteAverage = serie.voteAverage,
        voteCount = serie.voteCount,
        releaseDate = String()
    )
}

private const val IMAGE_ULR = "https://image.tmdb.org/t/p/w780/"
