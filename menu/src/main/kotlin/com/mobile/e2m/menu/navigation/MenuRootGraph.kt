package com.mobile.e2m.menu.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.mobile.e2m.menu.presentation.audioQuality.audioQualityDestination
import com.mobile.e2m.menu.presentation.driveMode.driveModeDestination
import com.mobile.e2m.menu.presentation.equalizer.equalizerDestination
import com.mobile.e2m.menu.presentation.localFile.localFileDestination
import com.mobile.e2m.menu.presentation.memory.memoryDestination
import com.mobile.e2m.menu.presentation.privacySocial.privacySocialDestination
import com.mobile.e2m.menu.presentation.theme.themeDestination
import com.mobile.e2m.menu.presentation.timer.timerDestination

fun NavGraphBuilder.menuRootNavGraph(
    navController: NavHostController,
) {
    themeDestination(
        goBack = {
            navController.popBackStack()
        }
    )

    timerDestination(
        goBack = {
            navController.popBackStack()
        }
    )

    equalizerDestination(
        goBack = {
            navController.popBackStack()
        }
    )

    memoryDestination(
        goBack = {
            navController.popBackStack()
        }
    )

    localFileDestination(
        goBack = {
            navController.popBackStack()
        }
    )

    driveModeDestination(
        goBack = {
            navController.popBackStack()
        }
    )

    audioQualityDestination(
        goBack = {
            navController.popBackStack()
        }
    )

    privacySocialDestination(
        goBack = {
            navController.popBackStack()
        }
    )
}
