package com.example.wallpapercollect.presentation.ui.firstviews.getstarted

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallpapercollect.R
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.Url
import com.example.wallpapercollect.api.models.UserRegister
import com.example.wallpapercollect.presentation.ui.navigation.NavigationRouters
import com.example.wallpapercollect.presentation.ui.utils.logResButton
import com.example.wallpapercollect.presentation.ui.utils.textFieldLogRes
import com.example.wallpapercollect.presentation.ui.utils.textFieldLogResPass
import com.example.wallpapercollect.presentation.ui.utils.textHeaderLogRes
import com.example.wallpapercollect.presentation.ui.theme.blue500
import com.example.wallpapercollect.presentation.ui.theme.brand500
import com.example.wallpapercollect.presentation.ui.theme.gray40

import com.example.wallpapercollect.presentation.ui.theme.interFont
import com.example.wallpapercollect.presentation.viewmodel.auth.Register


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SetJavaScriptEnabled")
@Composable
fun RegisterEmailScreen(
    register: Register = hiltViewModel(),
    navController: NavController
) {
    val webView = WebView(LocalContext.current)
    val webViewSetting = webView.settings
    webViewSetting.javaScriptEnabled = true


    var isRegisterEmailDefaultSessionClicked by rememberSaveable { mutableStateOf(false) }
    var isRegisterGoogleSessionClicked by rememberSaveable { mutableStateOf(false) }
    var isRegisterFacebookSessionClicked by rememberSaveable { mutableStateOf(false) }

    val statusRegisterEmailDefaultSession = register.registerEmailDefaultStatus.collectAsState(Status("")).value
    val statusRegisterGoogleSession = register.registerGoogleSession.collectAsState(Url("","")).value
    val statusRegisterFacebookSession = register.registerFacebookSession.collectAsState(Url("","")).value


    var email by rememberSaveable { mutableStateOf("") }
    var fullName by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    Column {

        Scaffold(
            topBar = {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.back_button),
                        contentDescription = "Back",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { navController.popBackStack() },
                        tint = Color.Unspecified
                    )
                }
            },
            content = { BodyRegisterEmail(
                email = { email = it },
                phoneNumber = { phoneNumber = it },
                fullName = { fullName = it },
                password = { password = it },
                confirmPassword = { confirmPassword = it },
                onClickRegisterDefault = {register.postRegisterEmailDefault(
                    UserRegister(
                        userName = fullName,
                        phoneNumber = phoneNumber,
                        password = password,
                        email = email
                    )
                )
                                         isRegisterEmailDefaultSessionClicked = true
                                         },
                onClickRegisterGoogle = {
                    register.getRegisterGoogleSession()
                    isRegisterGoogleSessionClicked = true
                                        },
                onClickRegisterFacebook = {
                    register.getRegisterFacebookSession()
                    isRegisterFacebookSessionClicked = true
                                          },
                navController = navController
            )
            }
        )
    }

    //TODO do something if user insert invalid data
    if(
        statusRegisterEmailDefaultSession.status == "ok" &&
        isRegisterEmailDefaultSessionClicked
    ){
        isRegisterEmailDefaultSessionClicked = false

        navController.navigate(NavigationRouters.LOGIN)
        navController.popBackStack(NavigationRouters.LOGIN,false)
        return
    }
    if (
        statusRegisterGoogleSession.status == "ok"&&
        isRegisterGoogleSessionClicked
    ){
        isRegisterGoogleSessionClicked = false

        webView.loadUrl(statusRegisterGoogleSession.url)
        return
    }
    if (
        statusRegisterFacebookSession.status == "ok"&&
        isRegisterFacebookSessionClicked
    ){
        isRegisterFacebookSessionClicked = false

        webView.loadUrl(statusRegisterFacebookSession.url)
        return
    }
}



@Composable
fun BodyRegisterEmail(
    email: (String) -> Unit,
    fullName: (String) -> Unit,
    phoneNumber: (String) -> Unit,
    password: (String) -> Unit,
    confirmPassword: (String) -> Unit,
    onClickRegisterDefault: () -> Unit,
    onClickRegisterGoogle: () -> Unit,
    onClickRegisterFacebook: () -> Unit,
    navController: NavController
) {

    Column(modifier = Modifier.padding(top = 114.dp, start = 24.dp, end = 22.dp)) {

        textHeaderLogRes(header = "Getting Started", description = "Create an account to continue!")
        Spacer(modifier = Modifier.padding(top = 28.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {



            textFieldLogRes(placeHolder = "Enter your Email", content = {email(it)})
            Spacer(modifier = Modifier.padding(top = 16.dp))

            textFieldLogRes(placeHolder = "Full name", content = {fullName(it)})
            Spacer(modifier = Modifier.padding(top = 16.dp))

            textFieldLogRes(placeHolder = "Phone number", content = {phoneNumber(it)})
            Spacer(modifier = Modifier.padding(top = 16.dp))

            textFieldLogResPass(placeHolder = "Password", content = {password(it)})
            Spacer(modifier = Modifier.padding(top = 16.dp))

            textFieldLogRes(placeHolder = "Confirm Password", content = {confirmPassword(it)})
            Spacer(modifier = Modifier.padding(top = 28.dp))

            logResButton(textButton = "Register", onClickable = onClickRegisterDefault)
            Spacer(modifier = Modifier.padding(top = 28.dp))
            Text(
                text = "Or Continue with Social Account",
                fontFamily = interFont,
                fontWeight = FontWeight.Normal,
                color = gray40,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.padding(top = 24.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = onClickRegisterFacebook ,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = blue500
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .height(56.dp)
                        .weight(7f)
                ) {
                    Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.facebook_logo),
                            contentDescription = "facebook",
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.padding(start = 12.dp))
                        Text(
                            text = "Facebook",
                            fontSize = 14.sp,
                            fontFamily = interFont,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }

                }
                Spacer(modifier = Modifier
                    .padding(start = 17.dp)
                    .weight(1f))
                Button(
                    onClick = onClickRegisterGoogle,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .height(56.dp)
                        .weight(7f)
                        .border(1.dp, gray40, RoundedCornerShape(10.dp)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.google_logo),
                            contentDescription = "google",
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.padding(start = 12.dp))
                        Text(
                            text = "Google",
                            fontSize = 14.sp,
                            fontFamily = interFont,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(top = 22.dp))
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
                    {navController.navigate(NavigationRouters.LOGIN){launchSingleTop = true}}
                )
            }
        }

    }
}