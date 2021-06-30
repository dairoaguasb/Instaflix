package dairo.aguas.instaflix.ui.model

import dairo.aguas.instaflix.domain.model.Movie

/**
 * Created by Dairo Aguas B on 29/06/2021.
 */
data class MovieViewData(
    val id: Int,
    val posterPath: String,
    val title: String
) {
    constructor(movies: Movie) : this(
        id = movies.id,
        posterPath = movies.posterPath,
        title = movies.title
    )
}
