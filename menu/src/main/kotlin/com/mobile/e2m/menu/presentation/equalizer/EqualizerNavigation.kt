package com.mobile.e2m.menu.presentation.equalizer

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToEqualizer(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Daily.Menu.Equalizer, navOptions)
}

internal fun NavGraphBuilder.equalizerDestination(
    goBack: () -> Unit = { },
) {
    composable<AppNavigationRoute.Daily.Menu.Equalizer> {
        EqualizerScreen(
            goBack = { goBack() }
        )
    }
}
