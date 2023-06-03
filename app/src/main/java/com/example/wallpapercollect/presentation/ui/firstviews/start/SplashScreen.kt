package com.example.wallpapercollect.presentation.ui.firstviews.start

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallpapercollect.presentation.ui.navigation.NavigationRouters
import com.example.wallpapercollect.presentation.viewmodel.profile.Profile
import com.example.wallpapercollect.splashlightv3.SplashLightV3
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import com.example.wallpapercollect.splashlightv3.SplashLightV3
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(context)
    SplashLightV3()

    var navigateStart by remember{ mutableStateOf(false) }
    LaunchedEffect(true){
        delay(3000)
        navigateStart = true
    }

    if(navigateStart){
        navigateStart = false
        if (account!=null) {
            navController.navigate(NavigationRouters.WALLPAPER){
                popUpTo(NavigationRouters.SPLASHSCREEN){inclusive = true}
            }
        }else if(isFirstTimeUser(context)){
            markUserAsNotFirstTime(context)
            navController.navigate(NavigationRouters.GET_STARTED){
                popUpTo(NavigationRouters.SPLASHSCREEN){
                    inclusive = true
                }
            }
        }else if(!isFirstTimeUser(context)){
            navController.navigate(NavigationRouters.LOGIN){
                popUpTo(NavigationRouters.SPLASHSCREEN){
                    inclusive = true
                }
            }
        }
    }




}

private fun isFirstTimeUser(context : Context): Boolean {
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    return sharedPrefs.getBoolean("isFirstTimeUser", true)
}
private fun markUserAsNotFirstTime(context : Context) {
    val sharedPrefs = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    sharedPrefs.edit().putBoolean("isFirstTimeUser", false).apply()
}


