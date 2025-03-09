package com.mobile.e2m.playmusic.presentation.playmusic

import com.mobile.e2m.core.datasource.remote.firebase.data.entity.SongsEntity

data class PlaymusicState(
    val isLoadingSong: Boolean = false,
    val songsList: List<SongsEntity>? = null,
)
