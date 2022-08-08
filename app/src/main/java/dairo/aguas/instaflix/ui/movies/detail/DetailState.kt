package dairo.aguas.instaflix.ui.movies.detail

import dairo.aguas.instaflix.ui.model.DetailViewData

data class DetailState(
    val loading: Boolean = false,
    val detailViewData: DetailViewData? = null,
    val error: Int = 0
)
