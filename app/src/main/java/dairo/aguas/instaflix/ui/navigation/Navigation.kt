package dairo.aguas.instaflix.ui.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dairo.aguas.instaflix.ui.home.HomeTabScreen
import dairo.aguas.instaflix.ui.home.HomeTabStateHolder
import dairo.aguas.instaflix.ui.home.HomeTabs
import dairo.aguas.instaflix.ui.home.NavArg
import dairo.aguas.instaflix.ui.home.NavCommand
import dairo.aguas.instaflix.ui.movies.detail.MovieDetailScreen

@Composable
fun Navigation(
    navController: NavHostController,
    tabStateHolder: HomeTabStateHolder
) {
    NavHost(
        navController = navController,
        startDestination = Feature.HOME.route
    ) {
        homeTabNav(navController, tabStateHolder)
    }
}

fun NavGraphBuilder.homeTabNav(
    navController: NavController,
    tabStateHolder: HomeTabStateHolder
) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.HOME).route,
        route = Feature.HOME.route
    ) {
        composable(NavCommand.ContentType(Feature.HOME)) {
            HomeTabScreen(
                viewModel = hiltViewModel(),
                tabStateHolder = tabStateHolder
            ) { id, tab ->
                val route = if (tab == HomeTabs.MOVIES) {
                    NavCommand.ContentTypeDetail(Feature.MOVIES).createRoute(id)
                } else {
                    NavCommand.ContentTypeDetail(Feature.SERIES).createRoute(id)
                }
                navController.navigate(route)
            }
        }
        composable(NavCommand.ContentTypeDetail(Feature.MOVIES)) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt(NavArg.ItemId.key) ?: 0
            MovieDetailScreen(
                viewModel = hiltViewModel(),
                movieId = id
            )
        }
        composable(NavCommand.ContentTypeDetail(Feature.SERIES)) {
            Text(text = "Detail")
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
