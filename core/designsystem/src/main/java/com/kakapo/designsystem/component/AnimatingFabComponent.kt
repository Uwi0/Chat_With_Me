package com.kakapo.designsystem.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.util.lerp
import kotlin.math.roundToInt

private const val FAB_TRANSITION = "fab_transition"
private const val FAB_TEXT_OPACITY = "fab_text_opacity"
private const val FAB_WITH_FACTOR = "fab_with_factor"
private const val TRANSITION_DURATION = 200

@Composable
fun AnimateFabContent(
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    extended: Boolean = true
) {

    val currentState = if (extended) ExpandableFabStates.Extended else ExpandableFabStates.Collapsed
    val transition = updateTransition(currentState, FAB_TRANSITION)

    val textOpacity by transition.animateFloat(
        transitionSpec = {
            if (targetState == ExpandableFabStates.Collapsed) {
                tween(
                    easing = LinearEasing,
                    durationMillis = (TRANSITION_DURATION / 12f * 5).roundToInt()
                )
            } else {
                tween(
                    easing = LinearEasing,
                    delayMillis = (TRANSITION_DURATION / 3f).roundToInt(),
                    durationMillis = (TRANSITION_DURATION / 12f * 5).roundToInt()
                )
            }
        },
        label = FAB_TEXT_OPACITY
    ) { state ->
        if (state == ExpandableFabStates.Collapsed) {
            0f
        } else {
            1f
        }
    }

    val fabWithFactor by transition.animateFloat(
        transitionSpec = {
            if (targetState == ExpandableFabStates.Collapsed) {
                tween(
                    easing = FastOutSlowInEasing,
                    durationMillis = TRANSITION_DURATION
                )
            } else {
                tween(
                    easing = FastOutSlowInEasing,
                    durationMillis = TRANSITION_DURATION
                )
            }
        },
        label = FAB_WITH_FACTOR
    ) { state ->
        if (state == ExpandableFabStates.Collapsed) {
            0f
        } else {
            1f
        }
    }

    IconAndTextRow(
        icon = icon,
        text = text,
        opacityProgress = { textOpacity },
        widthProgress = { fabWithFactor },
        modifier = modifier
    )
}

@Composable
private fun IconAndTextRow(
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    opacityProgress: () -> Float,
    widthProgress: () -> Float,
    modifier: Modifier
) {
    Layout(
        modifier = modifier,
        content = {
            icon()
            Box(modifier = Modifier.graphicsLayer { alpha = opacityProgress() }) {
                text()
            }
        }
    ) { measurable, constrain ->

        val iconPlaceable = measurable[0].measure(constrain)
        val textPlaceable = measurable[1].measure(constrain)

        val height = constrain.maxHeight
        val initialWidth = height.toFloat()

        val iconPadding = (initialWidth - iconPlaceable.width) / 2f

        val expandedWidth = iconPlaceable.width + textPlaceable.width + iconPadding * 3

        val width = lerp(initialWidth, expandedWidth, widthProgress())
        layout(width.roundToInt(), height){
            iconPlaceable.place(
                iconPadding.roundToInt(),
                constrain.maxHeight / 2 - iconPlaceable.height / 2
            )
            textPlaceable.place(
                (iconPlaceable.width + iconPadding * 2).roundToInt(),
                constrain.maxHeight / 2 - textPlaceable.height / 2
            )
        }
    }
}

private enum class ExpandableFabStates { Collapsed, Extended }