package com.example.wallpapercollect.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wallpapercollect.presentation.ui.start.GetStarted
import com.example.wallpapercollect.presentation.ui.start.LoginScreen
import com.example.wallpapercollect.presentation.ui.start.RegisterEmailScreen
import com.example.wallpapercollect.presentation.ui.start.SplashScreen
import com.example.wallpapercollect.presentation.ui.home.downloadScreen
import com.example.wallpapercollect.presentation.ui.home.screenProfile


@Composable
fun WallpaperCollectAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationRouters.SPLASHSCREEN
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        composable(NavigationRouters.SPLASHSCREEN){
            SplashScreen(navController)
        }
        composable(NavigationRouters.GET_STARTED){
            GetStarted(navController = navController)
        }
        composable(NavigationRouters.LOGIN){
            LoginScreen(navHostController = navController)
        }
        composable(NavigationRouters.REGISTER){
            RegisterEmailScreen(navController = navController)
        }
        composable(NavigationRouters.PROFILE){
            screenProfile(navHostController = navController)
        }
        composable(NavigationRouters.WALLPAPER+"/{id}"){
            it.arguments?.getString("id")?.let { it1 -> downloadScreen(id = it1) }
        }
        //TODO make wallpaper page
    }

}