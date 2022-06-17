package dairo.aguas.instaflix.ui.series

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dairo.aguas.instaflix.ui.common.ErrorMessage
import dairo.aguas.instaflix.ui.common.ItemList
import dairo.aguas.instaflix.ui.common.LoadingIndicator
import dairo.aguas.instaflix.ui.home.InstaflixScreen
import dairo.aguas.instaflix.ui.model.ItemViewData
import dairo.aguas.instaflix.ui.ui.theme.Orange300
import dairo.aguas.instaflix.ui.utils.verticalGradientBackground

@Composable
fun SeriesScreen(
    viewModel: SeriesViewModel,
    lazyGridState: LazyGridState,
    openDetail: (Int) -> Unit
) {
    val seriesState by viewModel.state.collectAsState()
    ScreenState(
        seriesState = seriesState,
        lazyGridState = lazyGridState,
        openDetail = openDetail
    ) {
        viewModel.getSeriesPopular()
    }
}

@Composable
private fun ScreenState(
    seriesState: SeriesState,
    lazyGridState: LazyGridState,
    openDetail: (Int) -> Unit,
    onRefresh: () -> Unit
) {
    when {
        seriesState.loading -> {
            LoadingIndicator()
        }
        seriesState.error != 0 -> {
            ErrorMessage(
                message = stringResource(id = seriesState.error),
                onRefresh = onRefresh
            )
        }
        else -> {
            val colors = listOf(Orange300, Color.Black)
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalGradientBackground(colors)
            ) {
                ItemList(
                    items = seriesState.items,
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
            rememberLazyGridState(),
            openDetail = {},
        ) {
        }
    }
}
