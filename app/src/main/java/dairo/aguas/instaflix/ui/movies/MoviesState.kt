package dairo.aguas.instaflix.ui.movies

import dairo.aguas.instaflix.ui.model.ItemViewData

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
sealed class MoviesState {

    object Loading : MoviesState()

    class Success(val data: List<ItemViewData>) : MoviesState()

    class Error(val resource: Int) : MoviesState()
}
