package com.mobile.e2m.menu.presentation.localFile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToLocalFile(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Daily.Menu.LocalFile, navOptions)
}

internal fun NavGraphBuilder.localFileDestination(
    goBack: () -> Unit = { },
) {
    composable<AppNavigationRoute.Daily.Menu.LocalFile> {
        LocalFileScreen(
            goBack = { goBack() }
        )
    }
}
