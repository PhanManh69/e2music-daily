package com.mobile.e2m.playmusic.presentation.buttonSheet.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MSongsData
import com.mobile.e2m.core.ui.composable.E2MSongsItem
import com.mobile.e2m.core.ui.composable.debounceClickable
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.playmusic.model.PlaymusicSheetModel
import com.mobile.e2m.playmusic.presentation.playmusic.composable.PlaymusicSongPlaying

@Composable
internal fun ButtonSheetContent(
    modifier: Modifier = Modifier,
    isPlaying: Boolean = true,
    currentSong: PlaymusicSheetModel,
    songsList: List<SongsEntity>,
    playPauseOnClick: () -> Unit = { },
    playingSongOnClick: (SongsEntity) -> Unit = { },
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    Column(
        modifier
            .fillMaxSize()
            .background(color.surface.black600),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = size.stroke.thickX,
            color = color.border.blur2Light,
        )

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(
                top = size.spacing.small,
                bottom = size.spacing.small + bottomPadding,
            ),
        ) {
            items(songsList.size) {
                val isCurrentSong = currentSong.id == songsList[it].id

                if (isCurrentSong) {
                    Box(
                        modifier = Modifier.background(color.surface.shadowDark)
                    ) {
                        E2MSongsItem(
                            modifier = Modifier.padding(
                                horizontal = size.spacing.small,
                                vertical = size.spacing.small2x,
                            ),
                            iconId = if (isPlaying) R.drawable.ic_pause_circle_thin else R.drawable.ic_play_circle_thin,
                            songItem = E2MSongsData(
                                imageUrl = currentSong.imageUrl,
                                name = currentSong.name,
                                singer = currentSong.singer,
                            ),
                            iconOnClick = {
                                playPauseOnClick()
                            }
                        )
                    }

                    PlaymusicSongPlaying(
                        modifier = Modifier.padding(top = size.spacing.small)
                    )

                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = bottomPadding + size.spacing.small2x,
                                bottom = size.spacing.small,
                            ),
                        thickness = size.stroke.thick,
                        color = color.border.blackLight,
                    )
                } else {
                    E2MSongsItem(
                        modifier = Modifier
                            .padding(
                                horizontal = size.spacing.small,
                                vertical = size.spacing.small2x
                            )
                            .debounceClickable { playingSongOnClick(songsList[it]) },
                        songItem = songsList.map { item ->
                            E2MSongsData(
                                imageUrl = item.imageUrl,
                                name = item.name,
                                singer = item.singer,
                            )
                        }[it],
                        imageOnClick = { playingSongOnClick(songsList[it]) }
                    )
                }
            }
        }
    }
}
