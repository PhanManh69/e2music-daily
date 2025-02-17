package com.mobile.e2m.menu.data.local

import com.mobile.e2m.core.ui.R
import com.mobile.e2m.menu.presentation.MenuSheetButtonData
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

fun fakeMenuSheetButtonData(): PersistentList<MenuSheetButtonData> {
    val itemsList = persistentListOf(
        MenuSheetButtonData(
            iconId = R.drawable.ic_palette,
            title = R.string.theme,
        ),
        MenuSheetButtonData(
            iconId = R.drawable.ic_timer,
            title = R.string.timer,
        ),
        MenuSheetButtonData(
            iconId = R.drawable.ic_equalizer,
            title = R.string.equalizer,
        ),
        MenuSheetButtonData(
            iconId = R.drawable.ic_memory,
            title = R.string.memory,
        ),
        MenuSheetButtonData(
            iconId = R.drawable.ic_music_file,
            title = R.string.localFile,
        ),
        MenuSheetButtonData(
            iconId = R.drawable.ic_drive_mode,
            title = R.string.driveMode,
        ),
        MenuSheetButtonData(
            iconId = R.drawable.ic_speakers,
            title = R.string.audioQuality,
        ),
        MenuSheetButtonData(
            iconId = R.drawable.ic_users,
            title = R.string.privacySocial,
        ),
    )

    return itemsList
}
