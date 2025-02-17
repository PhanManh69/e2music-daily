package com.mobile.e2m.menu.presentation.memory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MHeader
import com.mobile.e2m.core.ui.composable.E2MScaffold
import com.mobile.e2m.core.ui.composable.background.E2MBackgroundDark
import com.mobile.e2m.core.ui.theme.Write100

@Composable
internal fun MemoryScreen(
    goBack: () -> Unit = { },
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        E2MBackgroundDark()

        E2MScaffold(
            topBar = {
                E2MHeader(
                    title = "Bộ nhớ",
                    leadingIconId = R.drawable.ic_angle_left,
                    leadingIconOnClick = { goBack() }
                )
            },
            content = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Đây là màn hình bộ nhớ",
                        color = Write100,
                    )
                }
            }
        )
    }
}
