package dairo.aguas.instaflix.ui.series

import dairo.aguas.instaflix.ui.model.SerieViewData

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
sealed class SeriesState {

    object Loading : SeriesState()

    class Success(val data: List<SerieViewData>) : SeriesState()

    class Error(val resource: Int) : SeriesState()

    object Empty : SeriesState()
}