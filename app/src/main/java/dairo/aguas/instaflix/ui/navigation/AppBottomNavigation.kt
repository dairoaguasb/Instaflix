package dairo.aguas.instaflix.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dairo.aguas.instaflix.ui.ui.theme.InstaflixTheme

@Composable
fun AppBottomNavigation(
    bottomNavOptions: List<NavItem>,
    currentRoute: String,
    onNavItemClick: (NavItem) -> Unit
) {
    BottomNavigation {
        bottomNavOptions.forEach { item ->
            val title = stringResource(id = item.title)
            BottomNavigationItem(
                selected = currentRoute.contains(item.navCommand.feature.route),
                onClick = { onNavItemClick(item) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = title
                    )
                },
                label = { Text(text = title) }
            )
        }
    }
}

@Preview
@Composable
fun AppBottomNavigationPreview() {
    InstaflixTheme {
        AppBottomNavigation(
            bottomNavOptions = listOf(NavItem.MOVIES, NavItem.SERIES),
            currentRoute = Feature.MOVIES.route,
        ) {
        }
    }
}
