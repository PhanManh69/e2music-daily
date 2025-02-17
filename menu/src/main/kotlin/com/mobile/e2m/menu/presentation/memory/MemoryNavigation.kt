package com.mobile.e2m.menu.presentation.memory

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToMemory(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Daily.Menu.Memory, navOptions)
}

internal fun NavGraphBuilder.memoryDestination(
    goBack: () -> Unit = { },
) {
    composable<AppNavigationRoute.Daily.Menu.Memory> {
        MemoryScreen(
            goBack = { goBack() }
        )
    }
}
