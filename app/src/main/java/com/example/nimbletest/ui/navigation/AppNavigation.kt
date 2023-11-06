package com.example.nimbletest.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nimbletest.domain.entities.Survey
import com.example.nimbletest.infrastructure.model.SurveyParamType
import com.example.nimbletest.ui.details.BlankScreen
import com.example.nimbletest.ui.details.DetailScreen
import com.example.nimbletest.ui.forgot.ForgotScreen
import com.example.nimbletest.ui.home.HomeScreen
import com.example.nimbletest.ui.home.HomeViewModel
import com.example.nimbletest.ui.login.LoginScreen
import com.example.nimbletest.ui.login.LoginViewModel
import com.example.nimbletest.ui.splash.view.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
        composable(AppScreens.SplashScreen.route) {
            SplashScreen(navController)

        }
        composable(AppScreens.LoginScreen.route,
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            }, popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
        ) {
            val viewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(loginViewModel = viewModel, navController)
        }
        composable(AppScreens.ForgotScreen.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            }
        ) {
            val viewModel = hiltViewModel<LoginViewModel>()
            ForgotScreen(navController, viewModel)
        }
        composable(AppScreens.HomeScreen.route,
            enterTransition = {
                EnterTransition.None
            }
        ) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(navController, viewModel)
        }
        composable(
            AppScreens.DetailScreen.route + "/{survey}",
            arguments = listOf(
                navArgument("survey") {
                    type = SurveyParamType()
                }
            )
        ) { navBackStackEntry ->
            val survey = navBackStackEntry.arguments?.getParcelable<Survey>("survey")
            DetailScreen(navController, survey = survey!!)
        }
        composable(AppScreens.BlankDetailsScreen.route){
            BlankScreen(navController)
        }
    }
}