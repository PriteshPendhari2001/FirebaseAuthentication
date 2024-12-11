package com.example.firebaseproject_2.Pages



import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.firebaseproject_2.AuthState
import com.example.firebaseproject_2.authViewModel

@Composable


fun SignupPage(modifier: Modifier = Modifier, navController: NavController, authViewModel: authViewModel){

    var email by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }

    // if anythig will change then this reflect on UI
    val authSate = authViewModel.authState.observeAsState()
    val context = LocalContext.current

// this composabel work when user is authenticated then this composable directly send to homepage
    LaunchedEffect(authSate.value){
        when(authSate.value){
            is AuthState.Authenticated -> navController.navigate("HomePage")
            is AuthState.Error -> Toast.makeText(context,
                (authSate.value as AuthState.Error).Message,Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Signup Page", fontSize = 30.sp)

        Spacer(Modifier.height(16.dp))


        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Spacer(Modifier.height(8.dp))


        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            authViewModel.signUp(email, password)
        }, enabled = authSate.value != AuthState.Loading){
            Text("Create Account")
        }

        Spacer(Modifier.height(8.dp))

        TextButton(onClick = { navController.navigate("login") }){
            Text("Allready have an Account!,Login")
        }
    }
}