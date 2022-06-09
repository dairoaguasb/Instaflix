package dairo.aguas.instaflix.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dairo.aguas.instaflix.ui.movies.MoviesScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Feature.MOVIES.route
    ) {
        moviesNav(navController)
        seriesNav(navController)
    }
}

private fun NavGraphBuilder.moviesNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.MOVIES).route,
        route = Feature.MOVIES.route
    ) {
        composable(NavCommand.ContentType(Feature.MOVIES)) {
            MoviesScreen()
        }
    }
}

private fun NavGraphBuilder.seriesNav(navController: NavController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.SERIES).route,
        route = Feature.SERIES.route
    ) {
        composable(NavCommand.ContentType(Feature.SERIES)) {
            MoviesScreen()
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
