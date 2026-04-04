package me.anasmusa.snowflake

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme())
            darkColorScheme()
        else
            lightColorScheme()
    ) {
        Scaffold {
            AppContent()
        }
    }
}

@Composable
fun AppContent(
    modifier: Modifier = Modifier
) {
    var density by remember { mutableStateOf(5f) }

    SnowflakeBox(
        modifier = modifier.fillMaxSize(),
        density = density.toInt(),
        color = if (isSystemInDarkTheme())
            Color.White.copy(alpha = 0.8f)
        else
            Color.Blue.copy(alpha = 0.8f)
    ) {
        Text(
            "Density: ${density.toInt()}",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 72.dp),
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


@Composable
@Preview
fun AppContentPreview() {
    AppContent()
}
