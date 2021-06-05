package br.com.android_master.loginapp

import android.util.Patterns
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun LoginScreen(
    onLoginButtonClick: ()-> Unit= {},
    onRegisterButtonClick: ()-> Unit= {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Login") },
            )
        }
    ) {
        LoginContent(onLoginButtonClick, onRegisterButtonClick)
    }
}

@ExperimentalAnimationApi
@Composable
fun LoginContent(
    onLoginButtonClick: ()-> Unit,
    onRegisterButtonClick: ()-> Unit
) {

    var name by remember { mutableStateOf("") }
    var nameHasError by remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var emailHasError by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf("") }
    var passwordHasError by remember { mutableStateOf(false) }


    var login by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 150.dp),
        Arrangement.Center
    ){
        AnimatedVisibility(
            visible = !login,
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                isError= nameHasError,
                onValueChange = { name = it},
                label = { Text("Name") }
            )
        }
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            isError= emailHasError,
            onValueChange = { email = it},
            label = { Text("Email") }
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            isError= passwordHasError,
            onValueChange = { password = it },
            label = { Text("Senha") }
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.align(Alignment.End)){
            MyButton(
                text = "Registrar",
                boolean= login,
                onTextButtonClick = { login = !login },
                onButtonClick = {
                    nameHasError = name.isBlank()
                    emailHasError =
                        if(email.isNotBlank()){
                            !checkEmailPattern(email)
                        }else{
                            email.isBlank()
                        }
                    passwordHasError = password.isBlank()

                    if(
                        !nameHasError and
                        !emailHasError and
                        !passwordHasError){

                        onRegisterButtonClick()
                    }
                }
            )
            Spacer(Modifier.width(16.dp))
            MyButton(
                text = "Login",
                boolean= !login,
                onTextButtonClick = { login = !login },
                onButtonClick = {
                    emailHasError =
                        if(email.isNotBlank()){
                            !checkEmailPattern(email)
                        }else{
                            email.isBlank()
                        }
                    passwordHasError = password.isBlank()

                    if(
                        !emailHasError and
                        !passwordHasError){

                        onLoginButtonClick()
                    }
                }
            )
        }
    }
}

@Composable
fun MyButton(
    text: String,
    boolean: Boolean = true,
    onTextButtonClick: ()->Unit,
    onButtonClick: ()->Unit
) {
    if(boolean){
        TextButton(
            onClick = onTextButtonClick
        ){
            Text(text)
        }
    }else{
        Button(
            onClick = onButtonClick,
            ){
            Text(text)
        }
    }
}



@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}


/**
 *  ==============================
 *         Kotlin Functions
 *  ==============================
 */


fun checkEmailPattern(string: String): Boolean{
    return Patterns.EMAIL_ADDRESS.matcher(string).matches()
}