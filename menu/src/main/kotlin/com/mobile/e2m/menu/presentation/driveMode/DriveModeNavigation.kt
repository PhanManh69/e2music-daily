package com.mobile.e2m.menu.presentation.driveMode

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToDriveMode(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Daily.Menu.DriveMode, navOptions)
}

internal fun NavGraphBuilder.driveModeDestination(
    goBack: () -> Unit = { },
) {
    composable<AppNavigationRoute.Daily.Menu.DriveMode> {
        DriveModeScreen(
            goBack = { goBack() }
        )
    }
}
