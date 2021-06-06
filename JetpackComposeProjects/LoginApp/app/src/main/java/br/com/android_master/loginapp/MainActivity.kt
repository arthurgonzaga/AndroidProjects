package br.com.android_master.loginapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.android_master.loginapp.ui.theme.LoginAppTheme
import br.com.android_master.loginapp.ui.theme.Util.hideKeyboard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private var currentUser: FirebaseUser? = null

    override fun onStart() {
        super.onStart()

        auth = FirebaseAuth.getInstance()
        currentUser = auth.currentUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp {
                if(currentUser == null){
                    LoginScreen(
                        onRegisterButtonClick= {
                            hideKeyboard(this)

                            /*TODO: register user*/
                        },
                        onLoginButtonClick = {
                            hideKeyboard(this)

                            /*TODO: login user*/
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    LoginAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginAppTheme {
        Greeting("Android")
    }
}