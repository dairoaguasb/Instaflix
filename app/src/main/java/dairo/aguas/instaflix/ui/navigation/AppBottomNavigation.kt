package dairo.aguas.instaflix.ui.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dairo.aguas.instaflix.ui.home.HomeTabs
import dairo.aguas.instaflix.ui.ui.theme.InstaflixTheme
import dairo.aguas.instaflix.ui.ui.theme.graySurface

@Composable
fun AppBottomNavigation(
    selectedTab: HomeTabs,
    onNavItemClick: (HomeTabs) -> Unit
) {
    val bottomNavBackground = if (isSystemInDarkTheme()) {
        graySurface
    } else {
        MaterialTheme.colors.background
    }

    val tabs = HomeTabs.values()

    BottomNavigation(backgroundColor = bottomNavBackground) {
        tabs.forEach { item ->
            val title = stringResource(id = item.title)
            BottomNavigationItem(
                selected = item == selectedTab,
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
            selectedTab = HomeTabs.MOVIES,
        ) {
        }
    }
}
