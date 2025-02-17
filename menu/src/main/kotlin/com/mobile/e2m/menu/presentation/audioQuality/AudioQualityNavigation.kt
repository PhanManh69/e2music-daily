package com.mobile.e2m.menu.presentation.audioQuality

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToAudioQuality(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Daily.Menu.AudioQuality, navOptions)
}

internal fun NavGraphBuilder.audioQualityDestination(
    goBack: () -> Unit = { },
) {
    composable<AppNavigationRoute.Daily.Menu.AudioQuality> {
        AudioQualityScreen(
            goBack = { goBack() }
        )
    }
}
