package com.example.wallpapercollect.presentation.ui.firstviews.getstarted

import android.content.Context
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.wallpapercollect.presentation.ui.navigation.NavigationRouters
import com.example.wallpapercollect.splashlightv3.SplashLightV3
import kotlinx.coroutines.delay


@Composable
fun splashScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    SplashLightV3()
    var navigateStart by remember{ mutableStateOf(false) }
    LaunchedEffect(true){
        delay(3000)
        navigateStart = true
    }
    if (navigateStart){
        if (isFirstTimeUser(context)){
            navHostController.navigate(NavigationRouters.GET_STARTED)
            navHostController.popBackStack(NavigationRouters.GET_STARTED, false)
            markUserAsNotFirstTime(context = context)
        }
        else
        {
            navHostController.navigate(NavigationRouters.LOGIN)
            navHostController.popBackStack(NavigationRouters.LOGIN,false)
        }
        //TODO make navigation to Home Screen when user has a token
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



@Preview
@Composable
fun prevSplashScreen() {
//    splashScreen()
}