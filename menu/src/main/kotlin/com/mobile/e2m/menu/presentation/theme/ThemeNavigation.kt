package com.mobile.e2m.menu.presentation.theme

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

fun NavController.goToTheme(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Daily.Menu.Theme, navOptions)
}

internal fun NavGraphBuilder.themeDestination(
    goBack: () -> Unit = { },
) {
    composable<AppNavigationRoute.Daily.Menu.Theme> {
        ThemeScreen(
            goBack = { goBack() }
        )
    }
}
