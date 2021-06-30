package dairo.aguas.instaflix.ui.model

import dairo.aguas.instaflix.domain.model.Serie

/**
 * Created by Dairo Aguas B on 29/06/2021.
 */
data class SerieViewData(
    val id: Int,
    val posterPath: String,
    val name: String
) {
    constructor(serie: Serie) : this(
        id = serie.id,
        posterPath = IMAGE_ULR + serie.posterPath,
        name = serie.title
    )
}

private const val IMAGE_ULR = "https://image.tmdb.org/t/p/w185/"