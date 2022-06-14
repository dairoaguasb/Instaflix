package dairo.aguas.instaflix.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dairo.aguas.instaflix.ui.home.HomeTabStateHolder
import dairo.aguas.instaflix.ui.movies.MoviesScreen
import dairo.aguas.instaflix.ui.series.SeriesScreen

@Composable
fun Navigation(
    navController: NavHostController,
    tabStateHolder: HomeTabStateHolder
) {
    NavHost(
        navController = navController,
        startDestination = Feature.MOVIES.route
    ) {
        moviesNav(navController, tabStateHolder)
        seriesNav(navController, tabStateHolder)
    }
}

private fun NavGraphBuilder.moviesNav(
    navController: NavController,
    tabStateHolder: HomeTabStateHolder
) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.MOVIES).route,
        route = Feature.MOVIES.route
    ) {
        composable(NavCommand.ContentType(Feature.MOVIES)) {
            MoviesScreen(
                viewModel = hiltViewModel(),
                lazyGridState = tabStateHolder.movieLazyGridState
            )
        }
    }
}

private fun NavGraphBuilder.seriesNav(
    navController: NavController,
    tabStateHolder: HomeTabStateHolder
) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.SERIES).route,
        route = Feature.SERIES.route
    ) {
        composable(NavCommand.ContentType(Feature.SERIES)) {
            SeriesScreen(hiltViewModel(), tabStateHolder.tvLazyGridState)
        }
    }
}

private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}
