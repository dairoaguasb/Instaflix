package dairo.aguas.instaflix.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import dairo.aguas.instaflix.R

enum class NavItem(
    val navCommand: NavCommand,
    val icon: ImageVector,
    @StringRes val title: Int
) {
    MOVIES(NavCommand.ContentType(Feature.MOVIES), Icons.Default.Movie, R.string.movies),
    SERIES(NavCommand.ContentType(Feature.SERIES), Icons.Default.Tv, R.string.series)
}

sealed class NavCommand(
    internal val feature: Feature,
    internal val subRoute: String = "home",
    private val navArgs: List<NavArg> = emptyList()
) {
    class ContentType(feature: Feature) : NavCommand(feature)

    class ContentTypeDetail(feature: Feature) :
        NavCommand(feature, "detail", listOf(NavArg.ItemId)) {
        fun createRoute(itemId: Int) = "${feature.route}/$subRoute/$itemId"
    }

    val route = run {
        val argValues = navArgs.map { "{${it.key}}" }
        listOf(feature.route)
            .plus(subRoute)
            .plus(argValues)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    ItemId("itemId", NavType.IntType)
}
