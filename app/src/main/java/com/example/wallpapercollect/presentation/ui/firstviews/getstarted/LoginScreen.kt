package com.example.wallpapercollect.presentation.ui.firstviews.getstarted

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.wallpapercollect.R
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.UserLogIn
import com.example.wallpapercollect.presentation.ui.navigation.NavigationRouters
import com.example.wallpapercollect.presentation.ui.utils.logResButton
import com.example.wallpapercollect.presentation.ui.utils.logResTripButton
import com.example.wallpapercollect.presentation.ui.utils.textFieldLogRes
import com.example.wallpapercollect.presentation.ui.utils.textFieldLogResPass
import com.example.wallpapercollect.presentation.ui.utils.textHeaderLogRes
import com.example.wallpapercollect.presentation.ui.theme.blue500
import com.example.wallpapercollect.presentation.ui.theme.brand500
import com.example.wallpapercollect.presentation.ui.theme.gray40
import com.example.wallpapercollect.presentation.ui.theme.interFont
import com.example.wallpapercollect.presentation.viewmodel.auth.Login


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginScreen(
    login: Login = hiltViewModel(),
    navHostController: NavHostController
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var loginEmailDefaultClicked by rememberSaveable {
        mutableStateOf(false)
    }
    var statusLoginSessionEmailDefault = login.loginEmailDefault.collectAsState().value
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
            content = { bodyLoginScreen(
                onClickLoginEmailDefault = {login.getLoginEmailDefault(
                    UserLogIn(
                        email = email,
                        password = password
                    )
                )},
                onClickLoginFacebookSession = {login.getLoginFacebookSession()},
                onClickLoginGoogleSession = {login.getLoginGoogleSession()},
                email = {email = it},
                password = {password = it},
                navHostController = navHostController
            )}
        )
    }
    if(
        (statusLoginSessionEmailDefault.status != "" || statusLoginSessionEmailDefault.status != "ok") &&
        (loginEmailDefaultClicked)
    ){
        //TODO do something in this condition
    }

    if(statusLoginSessionEmailDefault.status == "ok"&&loginEmailDefaultClicked){
        statusLoginSessionEmailDefault = Status("")
        loginEmailDefaultClicked = false
        navHostController.navigate(NavigationRouters.WALLPAPER)
        navHostController.popBackStack(NavigationRouters.WALLPAPER,false)
    }
}

@Composable
fun bodyLoginScreen(
    onClickLoginEmailDefault : () -> Unit,
    onClickLoginFacebookSession : () -> Unit,
    onClickLoginGoogleSession : () -> Unit,
    email : (String) -> Unit,
    password: (String) -> Unit,
    navHostController: NavHostController
) {


    Column(modifier = Modifier.padding(top = 114.dp, start = 24.dp, end = 24.dp)) {
//        var emailTextField by rememberSaveable { mutableStateOf("") }
//        var password by rememberSaveable { mutableStateOf("") }

        textHeaderLogRes(header = "Let’s Sign You in", description = "Welcome back, You‘ve  been missed")
        Spacer(modifier = Modifier.padding(top = 28.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            textFieldLogRes(placeHolder = "Enter your email", content = {email(it)})
            Spacer(modifier = Modifier.padding(top = 16.dp))

            textFieldLogResPass(placeHolder = "Enter your password", content = {password(it)})
            Spacer(modifier = Modifier.padding(top = 28.dp))

            logResButton(textButton = "Login") {/*TODO make login checker*/ onClickLoginEmailDefault}
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
                textButton = "Sign In With Facebook"
            ) {/*TODO Facebook API*/ onClickLoginFacebookSession }

            logResTripButton(
                icon = R.drawable.google_logo,
                colorIcon = Color.Unspecified,
                colorButton = Color.White,
                colorText = Color.Black,
                colorBorder = gray40,
                nameIcon = "google",
                textButton = "Sign In With Google"
            ) {/*TODO Google API*/ onClickLoginGoogleSession}
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

@Preview
@Composable
fun prevLoginScreen() {
//    loginScreen()
}

@Preview
@Composable
fun prevBodyLoginScreen() {
//    bodyLoginScreen()
}