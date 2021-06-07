package br.com.android_master.loginapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import br.com.android_master.loginapp.ui.model.User
import br.com.android_master.loginapp.ui.theme.LoginAppTheme
import br.com.android_master.loginapp.ui.theme.Util.hideKeyboard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.ktx.Firebase

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
                var loggedIn by remember{ mutableStateOf(currentUser != null) }

                if(!loggedIn){
                    LoginScreen(
                        onRegisterButtonClick= { user ->
                            hideKeyboard(this)

                            register(user){
                                loggedIn = it
                            }
                        },
                        onLoginButtonClick = { user ->
                            hideKeyboard(this)

                            login(user){
                                loggedIn = it
                            }
                        }
                    )
                }else{
                    UserScreen(
                        currentUser= currentUser,
                        onLogoutButtonClick = {
                            FirebaseAuth.getInstance().signOut();
                            loggedIn = false
                        }
                    )
                }
            }
        }
    }


    fun register(user: User, updateUI:(loggedIn: Boolean) -> Unit){
        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener(this@MainActivity) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "createUserWithEmail:success")
                    currentUser = auth.currentUser

                    // Change the user display name
                    val userProfileChangeRequest = UserProfileChangeRequest
                        .Builder().setDisplayName(user.name).build()
                    currentUser!!.updateProfile(userProfileChangeRequest)

                    // update the UI
                    login(user, updateUI)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(false)
                }
        }
    }

    fun login(user: User, updateUI:(loggedIn: Boolean) -> Unit){

        auth.signInWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    currentUser = auth.currentUser
                    updateUI(true)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(false)
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


@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginAppTheme {
        LoginScreen()
    }
}
