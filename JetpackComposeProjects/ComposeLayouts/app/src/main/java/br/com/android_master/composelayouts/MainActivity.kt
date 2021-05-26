package br.com.android_master.composelayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import br.com.android_master.composelayouts.ui.theme.ComposeLayoutsTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComposeLayoutsTheme {
                ProvideWindowInsets {
                    LayoutCodeLab()
                }
            }
        }
    }
}

@Composable
fun PhotographerCard(modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(18.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { }
            .fillMaxWidth()
            .padding(12.dp)
    ){
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {

        }
        Column(Modifier.padding(start= 16.dp)){
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            // LocalContentAlpha is defining opacity level of its children
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("Thanks for going through the Layouts codelab", style = MaterialTheme.typography.body2)
            }
        }
    }

}

@Composable
fun LayoutCodeLab() {

    val systemUiController = rememberSystemUiController()
    val darkColor = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setStatusBarColor(Color.Transparent, darkIcons = darkColor)
    }

    Scaffold(
        Modifier.statusBarsPadding(),
        topBar = {
            TopAppBar(
                title = {
                    Text("LayoutCodelab", color = MaterialTheme.colors.onBackground)
                },
                actions= {
                         IconButton(onClick = {}) {
                             Icon(Icons.Filled.Share, contentDescription = "Share")
                         }
                },
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.background,
            )
        }
    ){
        PhotographerCard(Modifier.padding(paddingValues = it))
    }
}

@Preview(showBackground = true)
@Composable
fun PhotographerCardPreview() {
    ComposeLayoutsTheme {
        LayoutCodeLab()
    }
}