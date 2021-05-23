package br.com.android_master.composebasics

import android.os.Bundle
import android.view.Gravity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.android_master.composebasics.ui.theme.ComposeBasicsTheme
import br.com.android_master.composebasics.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                Column{
                    NewsStory()
                    ListOfContent()
                }
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
            modifier = Modifier
                .clip(Shapes.medium)
                .height(180.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("A day wandering through the sandhills " +
                "in Shark Fin Cove, and a few of the " +
                "sights I saw",

            style = MaterialTheme.typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(Modifier.height(8.dp))
        Text("Davenport, California", style = MaterialTheme.typography.body2)
        Text("December 2018", style = MaterialTheme.typography.body2)
    
    
    }
}


@Composable
fun ListOfContent(names: List<String> = listOf("alo", "teste","top")) {

    val counterState = remember{ mutableStateOf(0) }

    Column(Modifier.fillMaxHeight()){
        Column(Modifier.weight(1f)){
            for(name in names){
                Text(name,
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 24.dp))
                Divider(color = Color.LightGray,
                    modifier = Modifier.padding(horizontal = 24.dp))
            }
        }

        Row(Modifier.fillMaxWidth(), Arrangement.Center){
            CounterButton(
                count = counterState.value,
                onClick = { newValue ->
                    counterState.value = newValue
                }
            )
        }
    }
}

@Composable
fun CounterButton(count: Int, onClick: (newValue: Int)-> Unit) {
    Button(modifier= Modifier.padding(24.dp), onClick = { onClick(count+1) }) {
        Text("You've clicked in this button $count times")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp{
        Column{
            NewsStory()
            ListOfContent()
        }
    }
}