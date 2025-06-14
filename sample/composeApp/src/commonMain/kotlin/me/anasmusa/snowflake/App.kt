package me.anasmusa.snowflake

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.anasmusa.snowflake.SnowflakeBox
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var density by remember { mutableStateOf(5f) }

        SnowflakeBox(
            modifier = Modifier.fillMaxSize(),
            density = density.toInt(),
            color = Color.Blue.copy(alpha = 0.8f)
        ) {
            Text(
                "Density: ${density.toInt()}",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 72.dp)
            )
            Slider(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp),
                value = density,
                steps = 8,
                valueRange = 1f..10f,
                onValueChange = {
                    density = it
                }
            )
        }
    }
}