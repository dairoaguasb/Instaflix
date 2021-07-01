package dairo.aguas.instaflix.ui.movies

import dairo.aguas.instaflix.ui.model.MovieViewData

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
sealed class MoviesState {

    object Loading : MoviesState()

    class Success(val data: List<MovieViewData>) : MoviesState()

    class Error(val resource: Int) : MoviesState()
}