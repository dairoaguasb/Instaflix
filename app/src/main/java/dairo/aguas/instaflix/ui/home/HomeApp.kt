package dairo.aguas.instaflix.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dairo.aguas.instaflix.ui.movies.MoviesScreen
import dairo.aguas.instaflix.ui.ui.theme.InstaflixTheme

@Composable
fun HomeApp() {
    InstaflixScreen {
        MoviesScreen()
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
