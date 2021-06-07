package br.com.android_master.loginapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@Composable
fun UserScreen(
    currentUser: FirebaseUser? = null,
    onLogoutButtonClick: ()-> Unit
) {
    Row(
        modifier= Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ){
        Text("Você está logado, ${currentUser!!.displayName}!")
        Button(
            onClick= onLogoutButtonClick
        ){
            Text("Sair")
        }
    }
}