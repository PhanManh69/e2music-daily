package com.mobile.e2m.playmusic.presentation.playmusic.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.mobile.e2m.core.ui.composable.E2MToggle
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.playmusic.presentation.getString

@Composable
internal fun PlaymusicSongPlaying(
    modifier: Modifier = Modifier,
    initChecked: Boolean = false,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    var checked by remember { mutableStateOf(initChecked) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = size.spacing.small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(size.spacing.small),
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(size.spacing.small4x),
        ) {
            Text(
                text = getString().autoplayTxt,
                style = style.base.regular,
                color = color.text.white,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Text(
                text = getString().explainAutoplayTxt,
                style = style.small.regular,
                color = color.text.white200,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        E2MToggle(
            initChecked = checked,
            onChecked = { checked = it }
        )
    }
}
