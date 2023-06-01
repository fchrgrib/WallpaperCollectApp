package com.example.wallpapercollect.presentation.ui.firstviews.getstarted

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.wallpapercollect.R
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.Token
import com.example.wallpapercollect.api.models.Url
import com.example.wallpapercollect.api.models.UserLogIn
import com.example.wallpapercollect.presentation.MainActivity
import com.example.wallpapercollect.presentation.ui.navigation.NavigationRouters
import com.example.wallpapercollect.presentation.ui.theme.blue500
import com.example.wallpapercollect.presentation.ui.theme.brand500
import com.example.wallpapercollect.presentation.ui.theme.gray40
import com.example.wallpapercollect.presentation.ui.theme.interFont
import com.example.wallpapercollect.presentation.ui.utils.logResButton
import com.example.wallpapercollect.presentation.ui.utils.logResTripButton
import com.example.wallpapercollect.presentation.ui.utils.textFieldLogRes
import com.example.wallpapercollect.presentation.ui.utils.textFieldLogResPass
import com.example.wallpapercollect.presentation.ui.utils.textHeaderLogRes
import com.example.wallpapercollect.presentation.viewmodels.auth.Login
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    login: Login = hiltViewModel(),
    navHostController: NavHostController
) {
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


    val statusLoginEmailDefaultSession = login.loginEmailDefault.collectAsState(Status("")).value
    val statusLoginGoogleSession = login.loginGoogleSession.collectAsState(Status("")).value
    val statusLoginFacebookSession = login.loginFacebookSession.collectAsState(Url("","")).value

    var isLoginEmailDefaultSessionClicked by rememberSaveable { mutableStateOf(false) }
    var isLoginGoogleSessionClicked by rememberSaveable { mutableStateOf(false) }
    var isLoginFacebookSessionClicked by rememberSaveable { mutableStateOf(false) }


    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }


    Column {

        Scaffold(
            topBar = {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.back_button),
                        contentDescription = "Back",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable {
                                navHostController.popBackStack()
                            },
                        tint = Color.Unspecified
                    )
                }
            },
            content = {
                BodyLoginScreen(
                    onClickLoginEmailDefault = {
                        login.getLoginEmailDefault(
                            UserLogIn(
                                email = email,
                                password = password
                            )
                        )
                        isLoginEmailDefaultSessionClicked = true
                    },
                    onClickLoginFacebookSession = {
                        login.getLoginFacebookSession()
                        isLoginFacebookSessionClicked = true
                    },
                    onClickLoginGoogleSession = {
                        isLoginGoogleSessionClicked = true
                        launcher.launch(gsc.signInIntent)
                    },
                    email = {email = it},
                    password = {password = it},
                    navHostController = navHostController
                )
            }
        )
    }
    //TODO do something if user insert invalid data
    if(
        statusLoginEmailDefaultSession.status == "ok"&&
        isLoginEmailDefaultSessionClicked
    ){
        isLoginEmailDefaultSessionClicked = false

        navHostController.navigate(NavigationRouters.WALLPAPER){
            popUpTo(NavigationRouters.LOGIN){ inclusive = true}
        }
        return
    }


    if(
        account!!.idToken != ""&&
        statusLoginGoogleSession.status == "ok"&&
        isLoginGoogleSessionClicked
    ){
        isLoginGoogleSessionClicked = false

        navHostController.navigate(NavigationRouters.WALLPAPER){
            popUpTo(NavigationRouters.LOGIN){inclusive = true}
        }
        return
    }
    if(
        account.idToken != ""&&
        isLoginGoogleSessionClicked
    ){
        login.postLoginGoogleSession(Token(account.idToken ?: ""))
        return
    }


    if(
        statusLoginFacebookSession.status == "ok"&&
        isLoginFacebookSessionClicked
    ){
        isLoginFacebookSessionClicked = false

//        TODO make facebook login session can use as mobile
        return
    }
}

@Composable
fun BodyLoginScreen(
    onClickLoginEmailDefault : () -> Unit,
    onClickLoginFacebookSession : () -> Unit,
    onClickLoginGoogleSession : () -> Unit,
    email : (String) -> Unit,
    password: (String) -> Unit,
    navHostController: NavHostController
) {

    Column(modifier = Modifier.padding(top = 114.dp, start = 24.dp, end = 24.dp)) {

        textHeaderLogRes(header = "Let’s Sign You in", description = "Welcome back, You‘ve  been missed")
        Spacer(modifier = Modifier.padding(top = 28.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            textFieldLogRes(placeHolder = "Enter your email", content = {email(it)})
            Spacer(modifier = Modifier.padding(top = 16.dp))

            textFieldLogResPass(placeHolder = "Enter your password", content = {password(it)})
            Spacer(modifier = Modifier.padding(top = 56.dp))

            logResButton(textButton = "Login", onClickable =onClickLoginEmailDefault /*TODO make login checker*/)
            Spacer(modifier = Modifier.padding(top = 28.dp))

            Text(
                text = "Or Continue with Social Account",
                fontFamily = interFont,
                fontWeight = FontWeight.Normal,
                color = gray40,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.padding(top = 28.dp))

            logResTripButton(
                icon = R.drawable.facebook_logo,
                colorIcon = Color.Unspecified,
                colorButton = blue500,
                colorText = Color.White,
                colorBorder = blue500,
                nameIcon = "facebook",
                textButton = "Sign In With Facebook",
                onClick = onClickLoginFacebookSession
            )

            logResTripButton(
                icon = R.drawable.google_logo,
                colorIcon = Color.Unspecified,
                colorButton = Color.White,
                colorText = Color.Black,
                colorBorder = gray40,
                nameIcon = "google",
                textButton = "Sign In With Google",
                onClick = onClickLoginGoogleSession
            )
            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            Row {
                Text(
                    text = "Don’t have  an  account? ",
                    fontFamily = interFont,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    fontSize = 12.sp
                )
                Text(
                    text = "Sign Up",
                    fontFamily = interFont,
                    fontWeight = FontWeight.SemiBold,
                    color = brand500,
                    fontSize = 12.sp,
                    modifier = Modifier.clickable
                    {navHostController.navigate(NavigationRouters.REGISTER) }
                )
            }
        }

    }


}

