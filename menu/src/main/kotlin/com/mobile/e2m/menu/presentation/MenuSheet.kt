package com.mobile.e2m.menu.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MIcon
import com.mobile.e2m.core.ui.composable.E2MImage
import com.mobile.e2m.core.ui.composable.debounceClickable
import com.mobile.e2m.menu.data.local.fakeMenuSheetButtonData

@Immutable
data class MenuSheetButtonData(
    val iconId: Int?,
    val title: Int?,
)

@Composable
fun MenuSheet(
    modifier: Modifier = Modifier,
    goToTheme: () -> Unit = { },
    goToTimer: () -> Unit = { },
    goToEqualizer: () -> Unit = { },
    goToMemory: () -> Unit = { },
    goToLocalFile: () -> Unit = { },
    goToDriveMode: () -> Unit = { },
    goToAudioQuality: () -> Unit = { },
    goToPrivacySocial: () -> Unit = { },
) {
    val context = LocalContext.current
    val color = E2MTheme.alias.color
    val size = E2MTheme.alias.size
    val style = E2MTheme.typography
    val topPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val listOnClick = listOf(
        goToTheme, goToTimer,  goToEqualizer,  goToMemory, goToLocalFile,
        goToDriveMode, goToAudioQuality, goToPrivacySocial,
    )

    Column(
        modifier = modifier.padding(top = topPadding + size.spacing.small2x),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = size.spacing.large),
            verticalArrangement = Arrangement.spacedBy(size.spacing.small),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            E2MImage(
                modifier = Modifier.size(size.icon.largeX),
                imageId = R.drawable.img_e2music,
            )

            Text(
                text = context.getString(R.string.appName),
                style = style.title.black,
                color = color.text.white,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(color = color.surface.backgroundDark),
        ) {
            fakeMenuSheetButtonData().forEachIndexed { index, item ->
                val onClick = listOnClick.getOrNull(index) ?: { }

                MenuSheetButtonItem(
                    item = item,
                    onClick = onClick,
                )
            }
        }
    }
}

@Composable
private fun MenuSheetButtonItem(
    modifier: Modifier = Modifier,
    item: MenuSheetButtonData,
    onClick: () -> Unit = { },
) {
    val context = LocalContext.current
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Column(
        modifier = modifier.debounceClickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(
                vertical = size.spacing.small, horizontal = size.spacing.small
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(size.spacing.small),
        ) {
            E2MIcon(
                modifier = Modifier.size(size.icon.small1X),
                iconId = item.iconId,
                tint = color.icon.blue2Light,
            )

            item.title?.let {
                Text(
                    modifier = Modifier.weight(1f),
                    text = context.getString(it),
                    style = style.title.bold,
                    color = color.text.white,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(),
            thickness = size.stroke.thick,
            color = color.border.blackLight,
        )
    }
}
