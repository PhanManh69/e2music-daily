package com.mobile.e2m.playmusic.presentation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.platform.LocalContext
import com.mobile.e2m.core.ui.R

@Immutable
data class GetStrings(
    val playmusicTxt: String,
    val commentTxt: String,
    val saveTxt: String,
    val shareTxt: String,
    val autoplayTxt: String,
    val explainAutoplayTxt: String,
    val nextSongTxt: String,
) {
    companion object {
        @Composable
        internal fun default(
            context: Context = LocalContext.current,
        ) = GetStrings(
            playmusicTxt = context.getString(R.string.playmusic),
            commentTxt = context.getString(R.string.comment),
            saveTxt = context.getString(R.string.save),
            shareTxt = context.getString(R.string.share),
            autoplayTxt = context.getString(R.string.autoplay),
            explainAutoplayTxt = context.getString(R.string.explainAutoplay),
            nextSongTxt = context.getString(R.string.nextSong),
        )
    }
}

@Composable
internal fun getString(): GetStrings {
    return GetStrings.default()
}
