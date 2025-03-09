package com.mobile.e2m.playmusic.presentation.playmusic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MHeader
import com.mobile.e2m.core.ui.composable.E2MMarqueeText
import com.mobile.e2m.core.ui.composable.scaffold.E2MScaffold
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.playmusic.model.PlaymusicSheetModel
import com.mobile.e2m.playmusic.presentation.buttonSheet.ButtonSheet
import com.mobile.e2m.playmusic.presentation.getString
import com.mobile.e2m.playmusic.presentation.playmusic.composable.PlaymusicButtonAction
import com.mobile.e2m.playmusic.presentation.playmusic.composable.PlaymusicButtonControl
import com.mobile.e2m.playmusic.presentation.playmusic.composable.PlaymusicTimeControl
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlaymusicSheet(
    duration: String? = null,
    currentPosition: String? = null,
    isPlaying: Boolean = true,
    currentSong: SongsEntity,
    goBack: () -> Unit = { },
    rewindOnClick: () -> Unit = { },
    forwardOnClick: () -> Unit = { },
    playPauseOnClick: () -> Unit = { },
    playingSongOnClick: (SongsEntity) -> Unit = { },
    viewModel: PlaymusicViewModel = koinViewModel()
) {
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()

    E2MScaffold(
        topBar = {
            E2MHeader(
                title = getString().playmusicTxt,
                leadingIconId = R.drawable.ic_angle_down,
                trailingIconId = R.drawable.ic_menu_dots_vertical,
                leadingIconOnClick = { goBack() }
            )
        },
        content = {
            PlaymusicContent(
                duration = duration,
                currentPosition = currentPosition,
                isPlaying = isPlaying,
                currentSong = currentSong,
                songsList = state.songsList ?: mutableListOf(),
                rewindOnClick = { rewindOnClick() },
                forwardOnClick = { forwardOnClick() },
                playPauseOnClick = { playPauseOnClick() },
                playingSongOnClick = { playingSongOnClick(it) },
            )
        }
    )
}

@Composable
private fun PlaymusicContent(
    modifier: Modifier = Modifier,
    duration: String? = null,
    currentPosition: String? = null,
    isPlaying: Boolean = true,
    currentSong: SongsEntity,
    songsList: List<SongsEntity>,
    rewindOnClick: () -> Unit = { },
    forwardOnClick: () -> Unit = { },
    playPauseOnClick: () -> Unit = { },
    playingSongOnClick: (SongsEntity) -> Unit = { },
    viewModel: PlaymusicViewModel = koinViewModel(),
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(size.spacing.largeX),
        ) {
            item {
                PlaymusicTimeControl(
                    modifier = Modifier
                        .padding(horizontal = size.spacing.small)
                        .padding(top = size.spacing.large),
                    imageUrl = currentSong.imageUrl,
                    durationSeconds = viewModel.parseTimeToSeconds(duration),
                    currentPositionSeconds = viewModel.parseTimeToSeconds(currentPosition),
                )

                Spacer(modifier = Modifier.height(size.spacing.small2x))

                Text(
                    modifier = Modifier.padding(horizontal = size.spacing.small),
                    text = "$currentPosition | $duration",
                    style = style.small.regular,
                    color = color.text.white600,
                )
            }

            item {
                E2MMarqueeText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = size.spacing.small),
                    text = currentSong.name ?: "Null",
                    gradientEdgeColor = Color.Transparent,
                    style = style.title.medium,
                    color = color.text.white200,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(size.spacing.small4x))

                Text(
                    modifier = Modifier.padding(horizontal = size.spacing.small),
                    text = currentSong.singer ?: "Null",
                    style = style.base.regular,
                    color = color.text.white600,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            item {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = size.stroke.thick,
                    color = color.border.blackLight,
                )
            }

            item {
                PlaymusicButtonAction()
            }

            item {
                PlaymusicButtonControl(
                    modifier = Modifier.padding(horizontal = size.spacing.small),
                    isPlaying = isPlaying,
                    rewindOnClick = { rewindOnClick() },
                    playingOnClick = { playPauseOnClick() },
                    forwardOnClick = { forwardOnClick() },
                )
            }
        }

        ButtonSheet(
            modifier = Modifier.align(Alignment.BottomCenter),
            isPlaying = isPlaying,
            currentSong = PlaymusicSheetModel(
                id = currentSong.id,
                imageUrl = currentSong.imageUrl,
                name = currentSong.name,
                singer = currentSong.singer,
            ),
            songsList = songsList,
            playPauseOnClick = { playPauseOnClick() },
            playingSongOnClick = { playingSongOnClick(it) },
        )
    }
}
