package com.mobile.e2m.menu.presentation.privacySocial

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mobile.e2m.core.ui.navigation.route.AppNavigationRoute

internal fun NavController.goToPrivacySocial(navOptions: NavOptions? = null) {
    this.navigate(route = AppNavigationRoute.Daily.Menu.PrivacySocial, navOptions)
}

internal fun NavGraphBuilder.privacySocialDestination(
    goBack: () -> Unit = { },
) {
    composable<AppNavigationRoute.Daily.Menu.PrivacySocial> {
        PrivacySocialScreen(
            goBack = { goBack() }
        )
    }
}
