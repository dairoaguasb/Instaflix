package dairo.aguas.instaflix.ui.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dairo.aguas.instaflix.ui.movies.MoviesScreen
import dairo.aguas.instaflix.ui.navigation.AppBottomNavigation
import dairo.aguas.instaflix.ui.series.SeriesScreen

@Composable
fun HomeTabScreen(
    viewModel: HomeViewModel,
    tabStateHolder: HomeTabStateHolder,
    openDetail: (Int, HomeTabs) -> Unit
) {
    InstaflixScreen {
        val selectedTab by viewModel.state

        Scaffold(
            bottomBar = {
                AppBottomNavigation(
                    selectedTab = selectedTab,
                    onNavItemClick = {
                        viewModel.selectTab(it)
                    }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Crossfade(selectedTab) { homeTabs ->
                    when (homeTabs) {
                        HomeTabs.MOVIES -> MoviesScreen(
                            viewModel = hiltViewModel(),
                            lazyGridState = tabStateHolder.movieLazyGridState
                        ) { id ->
                            openDetail(id, homeTabs)
                        }
                        HomeTabs.SERIES -> SeriesScreen(
                            viewModel = hiltViewModel(),
                            lazyGridState = tabStateHolder.tvLazyGridState
                        ) { id ->
                            openDetail(id, homeTabs)
                        }
                    }
                }
            }
        }
    }
}
