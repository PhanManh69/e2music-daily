package com.mobile.e2m.playmusic.presentation.playmusic.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mobile.e2m.core.ui.composable.E2MIcon
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.debounceClickable

@Composable
internal fun PlaymusicButtonControl(
    modifier: Modifier = Modifier,
    isPlaying: Boolean = true,
    shuffleOnClick: () -> Unit = { },
    rewindOnClick: () -> Unit = { },
    playingOnClick: () -> Unit = { },
    forwardOnClick: () -> Unit = { },
    loopOnClick: () -> Unit = { },
) {
    val size = E2MTheme.alias.size.icon

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        E2MIcon(
            modifier = Modifier
                .size(size.small1X)
                .debounceClickable { shuffleOnClick() },
            iconId = R.drawable.ic_shuffle,
        )

        E2MIcon(
            modifier = Modifier
                .size(size.small)
                .debounceClickable { rewindOnClick() },
            iconId = R.drawable.ic_skip_previous
        )

        E2MIcon(
            modifier = Modifier
                .size(size.large1X)
                .debounceClickable {
                    playingOnClick()
                },
            iconId = if (isPlaying) R.drawable.ic_pause_circle_solid else R.drawable.ic_play_circle_solid
        )

        E2MIcon(
            modifier = Modifier
                .size(size.small)
                .debounceClickable { forwardOnClick() },
            iconId = R.drawable.ic_skip_next
        )

        E2MIcon(
            modifier = Modifier
                .size(size.small1X)
                .debounceClickable { loopOnClick() },
            iconId = R.drawable.ic_loop_square
        )
    }
}
