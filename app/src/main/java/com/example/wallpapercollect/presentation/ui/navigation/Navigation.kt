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
import com.google.android.gms.auth.api.signin.GoogleSignInClient


@Composable
fun WallpaperCollectAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationRouters.SPLASHSCREEN,
    gsc:GoogleSignInClient
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
            GetStarted(navController = navController, gsc = gsc)
        }
        composable(NavigationRouters.LOGIN){
            LoginScreen(navController = navController, gsc = gsc)
        }
        composable(NavigationRouters.REGISTER){
            RegisterEmailScreen(navController = navController, gsc = gsc)
        }
        composable(NavigationRouters.PROFILE){
            ScreenProfile(navController = navController,gsc=gsc)
        }
        composable(NavigationRouters.WALLPAPER+"/{id}/{name}"){
             DownloadScreen(
                 id = it.arguments?.getString("id")?:"",
                 imageName = it.arguments?.getString("name")?:"",
                 navController = navController
             )
        }
        composable(NavigationRouters.WALLPAPER){
            WallpaperCollectionScreen(navController = navController, gsc = gsc)
        }
        composable(NavigationRouters.AUTHOR){
            AuthorScreen(navController = navController)
        }
        composable(NavigationRouters.PRIVACY){
            PrivacyScreen(navController = navController)
        }
    }

}