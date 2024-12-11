package com.example.firebaseproject_2


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseproject_2.Pages.HomePage
import com.example.firebaseproject_2.Pages.LoginPage
import com.example.firebaseproject_2.Pages.SignupPage

@Composable

fun MyAppNavigation(modifier: Modifier = Modifier,authViewModel: authViewModel){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {

        composable("login"){
            LoginPage(modifier,navController,authViewModel)
        }

        composable("SignupPage"){
            SignupPage(modifier,navController,authViewModel)
        }

        composable("HomePage"){
            HomePage(modifier,navController,authViewModel)
        }
    })

}
