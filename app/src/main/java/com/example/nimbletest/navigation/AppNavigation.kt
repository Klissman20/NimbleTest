package com.example.nimbletest.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nimbletest.ui.login.view.LoginScreen
import com.example.nimbletest.ui.login.viewmodel.LoginViewModel
import com.example.nimbletest.ui.splash.view.SplashScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route){
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(AppScreens.LoginScreen.route){
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(loginViewModel = viewModel)
        }
    }
}