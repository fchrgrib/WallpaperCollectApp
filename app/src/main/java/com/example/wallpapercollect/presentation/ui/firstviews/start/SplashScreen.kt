package com.example.wallpapercollect.presentation.ui.firstviews.start


import android.content.Context
import android.util.Log
import android.webkit.CookieManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.wallpapercollect.api.ApiConstants
import com.example.wallpapercollect.presentation.ui.navigation.NavigationRouters
import com.example.wallpapercollect.splashlightv3.SplashLightV3
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavController
) {
    val context = LocalContext.current
    SplashLightV3()

    val regexPattern = Regex("token=")
    val input = CookieManager.getInstance().getCookie(ApiConstants.BASE_URL)?:""


    var navigateStart by remember{ mutableStateOf(false) }
    LaunchedEffect(true){
        delay(3000)
        navigateStart = true
    }

    if(navigateStart){
        navigateStart = false
        if (regexPattern.containsMatchIn(input)) {
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


