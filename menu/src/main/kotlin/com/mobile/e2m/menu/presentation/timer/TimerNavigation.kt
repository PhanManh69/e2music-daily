package com.mobile.e2m.menu.presentation.timer

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToTimer(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Daily.Menu.Timer, navOptions)
}

internal fun NavGraphBuilder.timerDestination(
    goBack: () -> Unit = { },
) {
    composable<AppNavigationRoute.Daily.Menu.Timer> {
        TimerScreen(
            goBack = { goBack() }
        )
    }
}
