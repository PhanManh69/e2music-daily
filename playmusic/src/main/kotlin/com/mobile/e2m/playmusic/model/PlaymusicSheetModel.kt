package com.mobile.e2m.playmusic.model

import androidx.compose.runtime.Immutable

@Immutable
data class PlaymusicSheetModel(
    val id: String? = null,
    val imageUrl: String? = null,
    val name: String? = null,
    val singer: String? = null,
)
