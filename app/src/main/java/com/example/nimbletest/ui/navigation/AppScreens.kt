package com.example.nimbletest.ui.navigation

sealed class AppScreens (val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object LoginScreen: AppScreens("login_screen")
    object ForgotScreen: AppScreens("forgot_screen")
    object HomeScreen: AppScreens("home_screen")
}