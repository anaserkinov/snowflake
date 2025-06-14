package me.anasmusa.snowflake

import androidx.annotation.IntRange
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color

@Composable
fun SnowflakeBox(
    modifier: Modifier = Modifier,
    @IntRange(from = 1L, to = 10L) density: Int,
    color: Color,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    val layer = remember(density, color) { SnowflakeLayer(density, color) }

    val redrawTrigger = remember { mutableStateOf(0L) }

    LaunchedEffect(layer) {
        while (true){
            withFrameMillis {
                layer.update(it)
                redrawTrigger.value++
            }
        }
    }

    Box(
        modifier = modifier.drawBehind {
            val value = redrawTrigger.value
            layer.draw(this)
        },
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
        content = content
    )
}