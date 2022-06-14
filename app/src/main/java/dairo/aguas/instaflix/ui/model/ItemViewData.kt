package dairo.aguas.instaflix.ui.model

import dairo.aguas.instaflix.domain.model.Movie
import dairo.aguas.instaflix.domain.model.Serie

/**
 * Created by Dairo Aguas B on 29/06/2021.
 */
data class ItemViewData(
    val id: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: String
) {
    constructor(movies: Movie) : this(
        id = movies.id,
        posterPath = IMAGE_ULR + movies.posterPath,
        title = movies.title,
        voteAverage = movies.voteAverage.toString()
    )

    constructor(series: Serie) : this(
        id = series.id,
        posterPath = IMAGE_ULR + series.posterPath,
        title = series.title,
        voteAverage = series.voteAverage.toString()
    )
}

private const val IMAGE_ULR = "https://image.tmdb.org/t/p/w185/"
