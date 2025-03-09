package com.mobile.e2m.playmusic.presentation.miniPlayer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MAsyncImage
import com.mobile.e2m.core.ui.composable.E2MIcon
import com.mobile.e2m.core.ui.composable.E2MMarqueeText
import com.mobile.e2m.core.ui.composable.debounceClickable
import com.mobile.e2m.core.ui.composable.shimmerEffect

@Composable
fun MiniPlayerSheet(
    modifier: Modifier = Modifier,
    currentSong: SongsEntity,
    isLoading: Boolean = false,
    isPlaying: Boolean = true,
    goToPlaymusic: () -> Unit = { },
    rewindOnClick: () -> Unit = { },
    forwardOnClick: () -> Unit = { },
    playPauseOnClick: () -> Unit = { },
) {
    val color = E2MTheme.alias.color
    val size = E2MTheme.alias.size
    val style = E2MTheme.typography

    Box(
        modifier = modifier
            .padding(size.spacing.small2x)
            .background(
                color = color.surface.shadowDark,
                shape = RoundedCornerShape(size.radius.radius2),
            )
            .clip(RoundedCornerShape(size.radius.radius2))
            .debounceClickable { goToPlaymusic() },
    ) {
        Row(
            modifier = Modifier.padding(size.spacing.small2x),
            horizontalArrangement = Arrangement.spacedBy(size.spacing.small),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .size(size.icon.large)
                        .clip(RoundedCornerShape(size.radius.radius2))
                        .shimmerEffect()
                )

                Spacer(modifier = Modifier.weight(1f))
            } else {
                E2MAsyncImage(
                    modifier = Modifier
                        .size(size.icon.large)
                        .clip(RoundedCornerShape(size.radius.radius2)),
                    imageUrl = currentSong.imageUrl,
                    contentDescription = currentSong.name,
                    contentScale = ContentScale.Crop,
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(size.spacing.small4x),
                ) {
                    E2MMarqueeText(
                        modifier = Modifier.fillMaxWidth(),
                        text = currentSong.name ?: "",
                        gradientEdgeColor = color.surface.shadowDark,
                        style = style.base.regular,
                        color = color.text.white200,
                    )

                    Text(
                        text = currentSong.singer ?: "",
                        style = style.small.regular,
                        color = color.text.white600,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }

            E2MIcon(
                modifier = Modifier
                    .size(size.icon.small1X)
                    .debounceClickable { rewindOnClick() },
                iconId = R.drawable.ic_skip_previous,
            )

            E2MIcon(
                modifier = Modifier
                    .size(size.icon.small1X)
                    .debounceClickable {
                        playPauseOnClick()
                    },
                iconId = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play,
            )

            E2MIcon(
                modifier = Modifier
                    .size(size.icon.small1X)
                    .debounceClickable { forwardOnClick() },
                iconId = R.drawable.ic_skip_next,
            )
        }
    }
}
