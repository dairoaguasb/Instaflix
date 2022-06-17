package dairo.aguas.instaflix.ui.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dairo.aguas.instaflix.R
import dairo.aguas.instaflix.ui.common.ErrorMessage
import dairo.aguas.instaflix.ui.common.ItemList
import dairo.aguas.instaflix.ui.common.LoadingIndicator
import dairo.aguas.instaflix.ui.home.InstaflixScreen
import dairo.aguas.instaflix.ui.model.ItemViewData
import dairo.aguas.instaflix.ui.utils.verticalGradientBackground

@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel,
    lazyGridState: LazyGridState,
    openDetail: (Int) -> Unit
) {
    val moviesState by viewModel.state.collectAsState()
    MoviesState(
        moviesState = moviesState,
        lazyGridState = lazyGridState,
        openDetail = openDetail
    ) {
        viewModel.getMoviesPopular()
    }
}

@Composable
private fun MoviesState(
    moviesState: MoviesState,
    lazyGridState: LazyGridState,
    openDetail: (Int) -> Unit,
    onRefresh: () -> Unit
) {
    when {
        moviesState.loading -> {
            LoadingIndicator()
        }
        moviesState.error != 0 -> {
            ErrorMessage(
                message = stringResource(id = moviesState.error),
                onRefresh = onRefresh
            )
        }
        else -> {
            val colors = listOf(
                MaterialTheme.colors.primary, Color.Black
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalGradientBackground(colors)
            ) {
                ItemList(
                    items = moviesState.items,
                    lazyGridState = lazyGridState,
                    openDetail = openDetail,
                    onRefresh = onRefresh
                )
            }
        }
    }
}

@Preview
@Composable
private fun MoviesStateLoadingPreview() {
    InstaflixScreen {
        MoviesState(
            moviesState = MoviesState(loading = true),
            lazyGridState = rememberLazyGridState(),
            openDetail = {},
        ) {
        }
    }
}

@Preview
@Composable
private fun MoviesStateErrorPreview() {
    InstaflixScreen {
        MoviesState(
            moviesState = MoviesState(
                error = R.string.error_time_out
            ),
            lazyGridState = rememberLazyGridState(),
            openDetail = {},
        ) {}
    }
}

@Preview
@Composable
private fun MoviesStateSuccessPreview() {
    InstaflixScreen {
        MoviesState(
            moviesState = MoviesState(
                items = listOf(
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
            ),
            lazyGridState = rememberLazyGridState(),
            openDetail = {},
        ) {
        }
    }
}
