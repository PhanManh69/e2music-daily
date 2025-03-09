package com.mobile.e2m.playmusic.presentation.playmusic.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import com.mobile.e2m.core.ui.composable.E2MAsyncImage
import com.mobile.e2m.core.ui.theme.E2MTheme
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

@Composable
internal fun PlaymusicTimeControl(
    modifier: Modifier = Modifier,
    durationSeconds: Float,
    currentPositionSeconds: Float,
    imageUrl: String? = null,
) {
    var currentProgress by remember { mutableFloatStateOf(0f) }

    val tokenSize = E2MTheme.alias.size
    val color = E2MTheme.alias.color

    LaunchedEffect(currentPositionSeconds) {
        currentProgress = (currentPositionSeconds / durationSeconds).coerceIn(0f, 1f)
    }

    Box(
        modifier = modifier.size(tokenSize.icon.large2X),
        contentAlignment = Alignment.Center
    ) {
        E2MAsyncImage(
            modifier = Modifier
                .size(tokenSize.icon.large2X)
                .clip(CircleShape),
            imageUrl = imageUrl,
        )

        Canvas(
            modifier = Modifier
                .size(tokenSize.icon.large2X)
                .pointerInput(Unit) {
                    detectDragGestures { change, _ ->
                        val touchX = change.position.x
                        val touchY = change.position.y
                        val center = Offset(
                            (size.width / 2).toFloat(),
                            (size.height / 2).toFloat(),
                        )
                        val deltaX = touchX - center.x
                        val deltaY = touchY - center.y
                        val angle = Math.toDegrees(atan2(deltaY, deltaX).toDouble())
                        val normalizedAngle = if (angle < 0) angle + 360 else angle
                        val progress = ((normalizedAngle + 90) % 360) / 360f

                        currentProgress = progress.toFloat()
                    }
                }
        ) {
            val strokeWidth = tokenSize.spacing.small4x.toPx()
            val radius = (size.minDimension - strokeWidth) / 2
            val center = Offset(x = size.width / 2, y = size.height / 2)

            drawCircle(
                color = color.border.whiteDark,
                radius = radius,
                style = Stroke(width = strokeWidth)
            )

            drawArc(
                color = color.border.blur2Light,
                startAngle = -90f,
                sweepAngle = 360f * currentProgress,
                useCenter = false,
                style = Stroke(width = strokeWidth),
                size = Size(width = radius * 2, height = radius * 2),
                topLeft = Offset(x = strokeWidth / 2, y = strokeWidth / 2),
            )

            val angleInRadians = Math.toRadians((360 * currentProgress - 90).toDouble())
            val dotX = center.x + radius * cos(angleInRadians).toFloat()
            val dotY = center.y + radius * sin(angleInRadians).toFloat()

            drawCircle(
                color = color.surface.blur2Light,
                radius = tokenSize.spacing.small3x.toPx(),
                center = Offset(dotX, dotY)
            )
        }
    }
}
