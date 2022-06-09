package dairo.aguas.instaflix.ui.movies

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dairo.aguas.instaflix.R
import dairo.aguas.instaflix.ui.common.ErrorMessage
import dairo.aguas.instaflix.ui.common.ItemList
import dairo.aguas.instaflix.ui.common.LoadingIndicator
import dairo.aguas.instaflix.ui.home.InstaflixScreen
import dairo.aguas.instaflix.ui.model.ItemViewData

@Composable
fun MoviesScreen(viewModel: MoviesViewModel = hiltViewModel()) {
    viewModel.getMoviesPopular()
    val moviesState by viewModel.state.collectAsState()
    MoviesState(moviesState = moviesState)
}

@Composable
private fun MoviesState(moviesState: MoviesState) {
    when (moviesState) {
        is MoviesState.Loading -> {
            LoadingIndicator()
        }
        is MoviesState.Error -> {
            ErrorMessage(message = stringResource(id = moviesState.resource))
        }
        is MoviesState.Success -> {
            ItemList(moviesState.data)
        }
    }
}

@Preview
@Composable
private fun MoviesStateLoadingPreview() {
    InstaflixScreen {
        MoviesState(
            moviesState = MoviesState.Loading
        )
    }
}

@Preview
@Composable
private fun MoviesStateErrorPreview() {
    InstaflixScreen {
        MoviesState(
            moviesState = MoviesState.Error(
                resource = R.string.error_time_out
            )
        )
    }
}

@Preview
@Composable
private fun MoviesStateSuccessPreview() {
    InstaflixScreen {
        MoviesState(
            moviesState = MoviesState.Success(
                listOf(
                    ItemViewData(
                        id = 1,
                        posterPath = "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
                        title = "Joker",
                        voteAverage = "7.0"
                    ),
                    ItemViewData(
                        id = 2,
                        posterPath = "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
                        title = "Back to the Future",
                        voteAverage = "7.0"
                    ),
                    ItemViewData(
                        id = 3,
                        posterPath = "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
                        title = "Batman",
                        voteAverage = "7.0"
                    ),
                    ItemViewData(
                        id = 4,
                        posterPath = "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
                        title = "Superman",
                        voteAverage = "7.0"
                    )
                )
            )
        )
    }
}
