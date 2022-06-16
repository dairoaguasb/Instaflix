package dairo.aguas.instaflix.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dairo.aguas.instaflix.ui.navigation.Navigation
import dairo.aguas.instaflix.ui.navigation.rememberInstaflixAppState
import dairo.aguas.instaflix.ui.ui.theme.InstaflixTheme

@Composable
fun HomeApp() {
    val appState = rememberInstaflixAppState()
    val tabStateHolder = HomeTabStateHolder(
        rememberLazyGridState(),
        rememberLazyGridState()
    )

    InstaflixScreen {
        Navigation(appState.navController, tabStateHolder)
    }
}

@Composable
fun InstaflixScreen(content: @Composable () -> Unit) {
    InstaflixTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Preview
@Composable
fun HomeAppPreview() {
    HomeApp()
}
