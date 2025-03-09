package com.mobile.e2m.playmusic.presentation.playmusic.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.mobile.e2m.core.ui.composable.E2MIcon
import com.mobile.e2m.core.ui.composable.debounceClickable
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.playmusic.presentation.getString

@Composable
internal fun PlaymusicButtonAction(
    modifier: Modifier = Modifier,
    numberFavourites: Int? = null,
    favouriteOnClick: () -> Unit = { },
    commentOnClick: () -> Unit = { },
    saveOnClick: () -> Unit = { },
    shareOnClick: () -> Unit = { },
) {
    val size = E2MTheme.alias.size.spacing
    val color = E2MTheme.alias.color

    var isFavourite by remember { mutableStateOf(false) }

    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = size.small),
        horizontalArrangement = Arrangement.spacedBy(size.small),
    ) {
        item {
            PlaymusicButtonActionItem(
                iconId = if (isFavourite) R.drawable.ic_like_solid else R.drawable.ic_like,
                title = numberFavourites.toString(),
                onClick = {
                    favouriteOnClick()
                    isFavourite = !isFavourite
                }
            )
        }

        item {
            PlaymusicButtonActionItem(
                iconId = R.drawable.ic_comment,
                title = getString().commentTxt,
                iconColor = color.icon.black400,
                titleColor = color.text.black400,
                onClick = { commentOnClick() }
            )
        }

        item {
            PlaymusicButtonActionItem(
                iconId = R.drawable.ic_bookmark,
                title = getString().saveTxt,
                onClick = { saveOnClick() }
            )
        }

        item {
            PlaymusicButtonActionItem(
                iconId = R.drawable.ic_share,
                title = getString().shareTxt,
                onClick = { shareOnClick() }
            )
        }
    }
}

@Composable
private fun PlaymusicButtonActionItem(
    modifier: Modifier = Modifier,
    iconId: Int? = null,
    title: String? = null,
    iconColor: Color = E2MTheme.alias.color.icon.blue2Light,
    titleColor: Color = E2MTheme.alias.color.text.white200,
    onClick: () -> Unit = { },
) {
    val color = E2MTheme.alias.color
    val size = E2MTheme.alias.size
    val style = E2MTheme.typography

    Box(
        modifier = modifier
            .background(
                color = color.surface.shadowDark,
                shape = RoundedCornerShape(size.radius.radius7),
            )
            .clip(RoundedCornerShape(size.radius.radius7))
            .debounceClickable { onClick() },
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = size.spacing.small,
                vertical = size.spacing.small3x,
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(size.spacing.smallX),
        ) {
            E2MIcon(
                modifier = Modifier.size(size.icon.small2X),
                iconId = iconId,
                tint = iconColor,
            )

            title?.let {
                Text(
                    text = it,
                    style = style.base.regular,
                    color = titleColor,
                )
            }
        }
    }
}
