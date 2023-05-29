package com.example.wallpapercollect.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wallpapercollect.presentation.ui.firstviews.getstarted.getStarted
import com.example.wallpapercollect.presentation.ui.firstviews.getstarted.loginScreen
import com.example.wallpapercollect.presentation.ui.firstviews.getstarted.registerEmailScreen
import com.example.wallpapercollect.presentation.ui.firstviews.getstarted.splashScreen
import com.example.wallpapercollect.presentation.ui.home.downloadScreen
import com.example.wallpapercollect.presentation.ui.home.screenProfile


@Composable
fun WallpaperCollectAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationRouters.SPLASHSCREEN  /*TODO make 3 condition to start destination*/
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        composable(NavigationRouters.SPLASHSCREEN){
            splashScreen(navController)  //TODO make navigation control
        }
        composable(NavigationRouters.GET_STARTED){
            getStarted(navHostController = navController)
        }
        composable(NavigationRouters.LOGIN){
            loginScreen(navHostController = navController)
        }
        composable(NavigationRouters.REGISTER){
            registerEmailScreen(navHostController = navController)
        }
        composable(NavigationRouters.PROFILE){
            screenProfile(navHostController = navController)
        }
        composable(NavigationRouters.WALLPAPER+"/{id}"){
            it.arguments?.getString("id")?.let { it1 -> downloadScreen(id = it1) }
        }
    }

}