package com.example.wallpapercollect.presentation.ui.firstviews.getstarted

import android.annotation.SuppressLint
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
import com.example.wallpapercollect.R
import com.example.wallpapercollect.api.models.UserRegister
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
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun registerEmailScreen(
    register: Register = hiltViewModel()
) {
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
                            .clickable {
                                // Implement back action here
                            },
                        tint = Color.Unspecified
                    )
                }
            },
            content = { body(
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
                )},
                onClickRegisterGoogle = {},
                onClickRegisterFacebook = {},
                onClickSignIn = {}
            )
            }
        )



    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun body(
    email : (String) -> Unit,
    fullName: (String) -> Unit,
    phoneNumber: (String) -> Unit,
    password: (String) -> Unit,
    confirmPassword : (String) -> Unit,
    onClickRegisterDefault : () -> Unit,
    onClickRegisterGoogle : () -> Unit,
    onClickRegisterFacebook : () -> Unit,
    onClickSignIn : () -> Unit
) {
//    var email by rememberSaveable { mutableStateOf("") }
//    var fullName by rememberSaveable { mutableStateOf("") }
//    var phoneNumber by rememberSaveable { mutableStateOf("") }
//    var password by rememberSaveable { mutableStateOf("") }
//    var confirmPassword by rememberSaveable { mutableStateOf("") }

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
                    onClick = { onClickRegisterFacebook },
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
                    {onClickSignIn}
                )
            }
        }

    }
}



@Preview
@Composable
fun prevRegisterEmail() {
    registerEmailScreen()
}

@Preview
@Composable
fun prevBody() {
//    body()
}
