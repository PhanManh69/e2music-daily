@file:OptIn(ExperimentalMaterial3Api::class)

package com.mobile.e2m.playmusic.presentation.buttonSheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize
import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity
import com.mobile.e2m.core.ui.composable.debounceClickable
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.playmusic.model.PlaymusicSheetModel
import com.mobile.e2m.playmusic.presentation.buttonSheet.composable.ButtonSheetContent
import com.mobile.e2m.playmusic.presentation.getString
import kotlinx.coroutines.launch

@Composable
internal fun ButtonSheet(
    modifier: Modifier = Modifier,
    isPlaying: Boolean = true,
    currentSong: PlaymusicSheetModel,
    songsList: List<SongsEntity>,
    playPauseOnClick: () -> Unit = { },
    playingSongOnClick: (SongsEntity) -> Unit = { },
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography
    val current = LocalDensity.current
    val scope = rememberCoroutineScope()
    val headerCollapsedSize = remember { mutableStateOf(Size.Zero) }
    val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
        skipHiddenState = true
    )

    BottomSheetScaffold(
        scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState),
        sheetPeekHeight = with(current) { headerCollapsedSize.value.height.toDp() },
        sheetContainerColor = color.surface.black600,
        sheetShape = RoundedCornerShape(
            topStart = size.radius.radius7,
            topEnd = size.radius.radius7,
        ),
        sheetShadowElevation = size.spacing.none,
        sheetDragHandle = {
            Box(
                modifier
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        headerCollapsedSize.value = it.size.toSize()
                    }
                    .debounceClickable {
                        scope.launch {
                            if (sheetState.currentValue == SheetValue.PartiallyExpanded) {
                                sheetState.expand()
                            } else {
                                sheetState.partialExpand()
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                if (sheetState.currentValue == SheetValue.Expanded) {
                    Text(
                        modifier = Modifier.padding(
                            top = size.spacing.small,
                            bottom = bottomPadding + size.spacing.small,
                        ),
                        text = getString().nextSongTxt.uppercase(),
                        style = style.title.bold,
                        color = color.text.blur2Light,
                    )
                } else {
                    Text(
                        modifier = Modifier.padding(
                            top = size.spacing.small,
                            bottom = bottomPadding + size.spacing.small,
                        ),
                        text = getString().nextSongTxt,
                        style = style.title.regular,
                        color = color.text.white600,
                    )
                }
            }
        },
        sheetContent = {
            ButtonSheetContent(
                isPlaying = isPlaying,
                currentSong = currentSong,
                songsList = songsList,
                playPauseOnClick = { playPauseOnClick() },
                playingSongOnClick = { playingSongOnClick(it) },
            )
        },
        content = { }
    )
}
