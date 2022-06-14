package dairo.aguas.instaflix.ui.series

import dairo.aguas.instaflix.ui.model.ItemViewData

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
data class SeriesState(
    val loading: Boolean = false,
    val items: List<ItemViewData> = emptyList(),
    val error: Int = 0
)
