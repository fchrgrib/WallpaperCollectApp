@file:JvmName("SplashWalperScreenKt")

package com.example.wallpapercollect.presentation.ui.start

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallpapercollect.R
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.Token
import com.example.wallpapercollect.api.models.Url
import com.example.wallpapercollect.presentation.MainActivity
import com.example.wallpapercollect.presentation.ui.navigation.NavigationRouters
import com.example.wallpapercollect.presentation.ui.utils.logResTripButton
import com.example.wallpapercollect.presentation.ui.theme.blue500
import com.example.wallpapercollect.presentation.ui.theme.brand500
import com.example.wallpapercollect.presentation.ui.theme.gray40
import com.example.wallpapercollect.presentation.ui.theme.interFont
import com.example.wallpapercollect.presentation.viewmodels.auth.Register
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


@Composable
fun GetStarted(navController: NavController, register: Register = hiltViewModel()) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {}
    )
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(stringResource(R.string.google_token))
        .requestProfile()
        .requestEmail()
        .build()

    val gsc = GoogleSignIn.getClient(MainActivity.instance,gso)
    val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(MainActivity.instance)


    var isRegisterGoogleSessionClicked by rememberSaveable { mutableStateOf(false) }
    var isRegisterFacebookSessionClicked by rememberSaveable { mutableStateOf(false) }

    val statusRegisterGoogleSession = register.registerGoogleSession.collectAsState(Status("")).value
    val statusRegisterFacebookSession = register.registerFacebookSession.collectAsState(Url("","")).value


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {

        Image(
            painter = painterResource(id = R.drawable.mason_grid),
            contentDescription = "mason grid",
            modifier = Modifier.size(450.dp)
        )

        Spacer(modifier = Modifier.padding(top = 17.dp))
        Text(
            text = "Only Wallpaper app you will ever need ...!",
            fontFamily = interFont,
            fontWeight = FontWeight.ExtraLight
        )

        Spacer(modifier = Modifier.padding(top = 40.dp))
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {

            logResTripButton(
                icon = R.drawable.sms,
                colorIcon = Color.White,
                colorButton = brand500,
                colorText = Color.White,
                colorBorder = brand500,
                nameIcon = "email",
                textButton = "Continue With Email"
            ) {
                navController.navigate(NavigationRouters.REGISTER)
            }

            logResTripButton(
                icon = R.drawable.google_logo,
                colorIcon = Color.Unspecified,
                colorButton = Color.White,
                colorText = Color.Black,
                colorBorder = gray40,
                nameIcon = "google",
                textButton = "Continue With Google"
            ) {
                isRegisterGoogleSessionClicked = true
                launcher.launch(gsc.signInIntent)
            }

            logResTripButton(
                icon = R.drawable.facebook_logo,
                colorIcon = Color.Unspecified,
                colorButton = blue500,
                colorText = Color.White,
                colorBorder = blue500,
                nameIcon = "facebook",
                textButton = "Continue With Facebook"
            ) {
//                TODO make facebook login session can use in mobile app
                isRegisterFacebookSessionClicked = true
            }

        }

        Spacer(modifier = Modifier.padding(top = 30.dp))
        Row {
            Text(text = "Already Have an Account ? ")
            Text(text = "Sig in", color = brand500, modifier = Modifier.clickable {
                navController.navigate(NavigationRouters.LOGIN){ launchSingleTop = true}
            })
        }
        Spacer(modifier = Modifier.padding(top = 15.dp))
    }

    if (
        account!!.idToken != ""&&
        statusRegisterGoogleSession.status == "ok"&&
        isRegisterGoogleSessionClicked
    ){
        isRegisterGoogleSessionClicked = false

        navController.navigate(NavigationRouters.WALLPAPER){
            popUpTo(NavigationRouters.GET_STARTED){inclusive = true}
        }
        return
    }
    if (
        account.idToken != ""&&
        isRegisterGoogleSessionClicked
    ){
        register.postRegisterGoogleSession(Token(account.idToken ?: ""))
        return
    }


    if (
        statusRegisterFacebookSession.status == "ok"&&
        isRegisterFacebookSessionClicked
    ){
        isRegisterFacebookSessionClicked = false

//        webView.loadUrl(statusRegisterFacebookSession.url)
        return
    }
}