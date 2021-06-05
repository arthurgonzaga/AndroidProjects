package br.com.android_master.loginapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Login") },
            )
        }
    ) {
        LoginContent()
    }
}

@Composable
fun LoginContent() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        Arrangement.Center
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it},
            label = { Text("Email") }
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") }
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.align(Alignment.End)){
            TextButton(
                onClick = {}
            ){
                Text("Registrar")
            }
            Spacer(Modifier.width(16.dp))
            Button(
                onClick = {}
            ){
                Text("Login")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}