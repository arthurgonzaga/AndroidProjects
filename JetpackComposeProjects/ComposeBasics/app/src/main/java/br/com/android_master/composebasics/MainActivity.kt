package br.com.android_master.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.android_master.composebasics.ui.theme.ComposeBasicsTheme
import br.com.android_master.composebasics.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeBasicsTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun NewsStory() {
    Column(Modifier.padding(16.dp)) {

        Image(
            painter = painterResource(R.drawable.header),
            contentDescription = null,
            modifier = Modifier.clip(Shapes.medium)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("A day in Shark Fin Cove")
        Text("Davenport, California")
        Text("December 2018")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsStory()
}