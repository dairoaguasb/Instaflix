package dairo.aguas.instaflix.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dairo.aguas.instaflix.ui.home.InstaflixScreen
import dairo.aguas.instaflix.ui.model.ItemViewData

@Composable
fun ItemList(
    items: List<ItemViewData>,
    lazyGridState: LazyGridState,
    openDetail: (Int) -> Unit,
    onRefresh: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (items.isNotEmpty()) {
            var isRefreshing by remember { mutableStateOf(false) }
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                onRefresh = {
                    isRefreshing = true
                    onRefresh.invoke()
                }
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(128.dp),
                    contentPadding = PaddingValues(4.dp),
                    state = lazyGridState
                ) {
                    items(items) {
                        CardListItem(
                            itemViewData = it,
                            modifier = Modifier.clickable { openDetail(it.id) }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ItemListPreview() {
    InstaflixScreen {
        ItemList(
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
            ),
            lazyGridState = rememberLazyGridState(),
            openDetail = {},
        ) {
        }
    }
}
