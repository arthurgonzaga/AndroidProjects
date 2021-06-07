package br.com.android_master.loginapp

import androidx.compose.foundation.indication
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@Composable
fun UserScreen(
    currentUser: FirebaseUser,
    onLogoutButtonClick: ()-> Unit
) {
    var isEmailVerified by remember{ mutableStateOf(currentUser.isEmailVerified) }

    if(isEmailVerified){
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Text("Você está logado, ${currentUser.displayName}!")
            Button(
                onClick= onLogoutButtonClick
            ){
                Text("Sair")
            }
        }
    }else{
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Text("Verifique seu email...")
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick= onLogoutButtonClick
                ){
                    Text("Sair")
                }
            }
        }
    }
}