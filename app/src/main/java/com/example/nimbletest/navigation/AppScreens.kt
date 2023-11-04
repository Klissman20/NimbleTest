package com.example.nimbletest.navigation

sealed class AppScreens (val route: String) {
    object SplashScreen: AppScreens ("splash_screen")
    object LoginScreen: AppScreens ("login_screen")
    object ForgotScreen: AppScreens ("forgot_screen")
}