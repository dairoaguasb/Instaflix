package dairo.aguas.instaflix.ui.series

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dairo.aguas.instaflix.ui.common.ErrorMessage
import dairo.aguas.instaflix.ui.common.ItemList
import dairo.aguas.instaflix.ui.common.LoadingIndicator
import dairo.aguas.instaflix.ui.home.InstaflixScreen
import dairo.aguas.instaflix.ui.model.ItemViewData

@Composable
fun SeriesScreen(
    viewModel: SeriesViewModel,
    lazyGridState: LazyGridState
) {
    val seriesState by viewModel.state.collectAsState()
    ScreenState(
        seriesState = seriesState,
        lazyGridState = lazyGridState
    )
}

@Composable
private fun ScreenState(
    seriesState: SeriesState,
    lazyGridState: LazyGridState
) {
    when {
        seriesState.loading -> {
            LoadingIndicator()
        }
        seriesState.error != 0 -> {
            ErrorMessage(message = stringResource(id = seriesState.error))
        }
        else -> {
            ItemList(
                items = seriesState.items,
                lazyGridState = lazyGridState
            )
        }
    }
}

@Preview
@Composable
private fun SeriesStateSuccessPreview() {
    InstaflixScreen {
        ScreenState(
            SeriesState(
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
            rememberLazyGridState()
        )
    }
}
