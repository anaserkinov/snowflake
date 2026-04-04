package me.anasmusa.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.anasmusa.androidapp.ui.theme.SnowflakeTheme
import me.anasmusa.snowflake.AppContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnowflakeTheme {
                Scaffold {
                    AppContent(modifier = Modifier.padding(it))
                }
            }
        }
    }
}
@Preview
@Composable
fun AppAndroidPreview() {
    AppContent()
}
