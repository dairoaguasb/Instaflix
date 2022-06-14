package dairo.aguas.instaflix.ui.movies

import dairo.aguas.instaflix.ui.model.ItemViewData

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
data class MoviesState(
    val loading: Boolean = false,
    val items: List<ItemViewData> = emptyList(),
    val error: Int = 0
)
