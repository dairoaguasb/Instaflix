package dairo.aguas.instaflix.ui.detail

import dairo.aguas.instaflix.ui.model.DetailViewData

/**
 * Created by Dairo Aguas B on 1/07/2021.
 */
sealed class DetailState {

    object Loading : DetailState()

    class Success(val data: DetailViewData) : DetailState()

    class Error(val resource: Int) : DetailState()
}