package dairo.aguas.instaflix.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dairo.aguas.instaflix.ui.navigation.AppBottomNavigation
import dairo.aguas.instaflix.ui.navigation.InstaflixAppState
import dairo.aguas.instaflix.ui.navigation.Navigation
import dairo.aguas.instaflix.ui.navigation.rememberInstaflixAppState
import dairo.aguas.instaflix.ui.ui.theme.InstaflixTheme

@Composable
fun HomeApp() {
    val appState = rememberInstaflixAppState()
    InstaflixScreen {
        Scaffold(
            bottomBar = {
                AppBottomNavigation(
                    bottomNavOptions = InstaflixAppState.BOTTOM_NAV_OPTIONS,
                    currentRoute = appState.currentRoute,
                    onNavItemClick = {
                        appState.onNavItemClick(it)
                    }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(appState.navController)
            }
        }
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
