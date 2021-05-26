package br.com.android_master.composelayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import br.com.android_master.composelayouts.ui.theme.ComposeLayoutsTheme
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyApp {
                LayoutCodeLab()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable ()-> Unit) {
    ComposeLayoutsTheme {
        ProvideWindowInsets {
            content()
        }
    }
}

@Composable
fun ListOfCards(scrollState: LazyListState ,paddingValues: PaddingValues) {



    scrollState.isScrollInProgress

    LazyColumn(
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(50){
            PhotographerCard(
                Modifier.padding(paddingValues = paddingValues),
                title = "Title",
                description = "Thanks for going through the Layouts codelab #$it"
            )
        }
    }
}

@Composable
fun PhotographerCard(
    modifier: Modifier = Modifier,
    title: String = "Title",
    description: String = "description"
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 18.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable { }
            .fillMaxWidth()
            .padding(12.dp)
    ){
        CustomImage()

        Column(Modifier.padding(start= 16.dp)){
            Text(title, fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(description, style = MaterialTheme.typography.body2)
            }
        }
    }

}

@Composable
fun CustomImage() {

    val colorLoading = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
    val colorSuccess = MaterialTheme.colors.background


    val painter = rememberCoilPainter(
        "https://developer.android.com/images/brand/Android_Robot.png",
        fadeIn = true
    )

    val circleBackgroundColor = animateColorAsState(
        if(painter.loadState  is ImageLoadState.Success) colorSuccess else colorLoading
    )


    Surface(
        modifier = Modifier.size(50.dp),
        shape = CircleShape,
        color = circleBackgroundColor.value
    ) {
        Image(
            modifier = Modifier.scale(0.85f),
            painter = painter,
            contentDescription = "android-logo",
        )
    }
}

@Composable
fun LayoutCodeLab() {
    val scrollState = rememberLazyListState()

    val systemUiController = rememberSystemUiController()
    val darkColor = MaterialTheme.colors.isLight
    SideEffect {
        systemUiController.setStatusBarColor(Color.Transparent, darkIcons = darkColor)
    }

    val elevationState = animateDpAsState(if(scrollState.firstVisibleItemIndex != 0) 8.dp else 0.dp)

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
                elevation = elevationState.value,
                backgroundColor = MaterialTheme.colors.background,
            )
        },
    ){
        ListOfCards(scrollState, it)
    }
}

@Preview(showBackground = true)
@Composable
fun PhotographerCardPreview() {
    ComposeLayoutsTheme {
        LayoutCodeLab()
    }
}