package com.example.firebaseproject_2.Pages



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebaseproject_2.AuthState
import com.example.firebaseproject_2.authViewModel

@Composable


fun HomePage(modifier: Modifier = Modifier, navController: NavController, authViewModel: authViewModel){

    val authSate = authViewModel.authState.observeAsState()

    LaunchedEffect(authSate.value){
        when(authSate.value){
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }


    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Home Page", fontSize = 32.sp)

        TextButton(onClick = {
            authViewModel.signout()
        }){
            Text("Sign Out")
        }
    }
}