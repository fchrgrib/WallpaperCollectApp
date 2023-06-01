package com.example.wallpapercollect.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wallpapercollect.presentation.ui.firstviews.start.GetStarted
import com.example.wallpapercollect.presentation.ui.firstviews.start.LoginScreen
import com.example.wallpapercollect.presentation.ui.firstviews.start.RegisterEmailScreen
import com.example.wallpapercollect.presentation.ui.firstviews.start.SplashScreen
import com.example.wallpapercollect.presentation.ui.home.AuthorScreen
import com.example.wallpapercollect.presentation.ui.home.DownloadScreen
import com.example.wallpapercollect.presentation.ui.home.PrivacyScreen
import com.example.wallpapercollect.presentation.ui.home.WallpaperCollectionScreen
import com.example.wallpapercollect.presentation.ui.home.ScreenProfile


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
            LoginScreen(navController = navController)
        }
        composable(NavigationRouters.REGISTER){
            RegisterEmailScreen(navController = navController)
        }
        composable(NavigationRouters.PROFILE){
            ScreenProfile(navController = navController)
        }
        composable(NavigationRouters.WALLPAPER+"/{id}"){
            it.arguments?.getString("id")?.let { it1 -> DownloadScreen(id = it1) }
        }
        composable(NavigationRouters.WALLPAPER){
            //TODO please test this
            WallpaperCollectionScreen(navController = navController)
        }
        composable(NavigationRouters.AUTHOR){
            //TODO please test this
            AuthorScreen(navController = navController)
        }
        composable(NavigationRouters.PRIVACY){
            //TODO please test this
            PrivacyScreen(navController = navController)
        }
    }

}