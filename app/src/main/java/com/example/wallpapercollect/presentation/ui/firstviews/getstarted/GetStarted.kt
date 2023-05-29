package com.example.wallpapercollect.presentation.ui.firstviews.getstarted

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.wallpapercollect.R
import com.example.wallpapercollect.presentation.ui.navigation.NavigationRouters
import com.example.wallpapercollect.presentation.ui.utils.logResTripButton
import com.example.wallpapercollect.presentation.ui.theme.blue500
import com.example.wallpapercollect.presentation.ui.theme.brand500
import com.example.wallpapercollect.presentation.ui.theme.gray40
import com.example.wallpapercollect.presentation.ui.theme.interFont
import com.example.wallpapercollect.presentation.viewmodel.auth.Register


@Composable
fun getStarted(navHostController: NavHostController, register: Register = hiltViewModel()) {
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
                navHostController.navigate(NavigationRouters.REGISTER)
                navHostController.popBackStack(NavigationRouters.REGISTER, false)
            }

            logResTripButton(
                icon = R.drawable.google_logo,
                colorIcon = Color.Unspecified,
                colorButton = Color.White,
                colorText = Color.Black,
                colorBorder = gray40,
                nameIcon = "google",
                textButton = "Continue With Google"
            ) { register.getRegisterGoogleSession()/*TODO Google API*/}

            logResTripButton(
                icon = R.drawable.facebook_logo,
                colorIcon = Color.Unspecified,
                colorButton = blue500,
                colorText = Color.White,
                colorBorder = blue500,
                nameIcon = "facebook",
                textButton = "Continue With Facebook"
            ) {register.getRegisterGoogleSession()/*TODO Facebook API*/ }


        }

        Spacer(modifier = Modifier.padding(top = 30.dp))
        Row {
            Text(text = "Already Have an Account ? ")
            Text(text = "Sig in", color = brand500, modifier = Modifier.clickable {
                navHostController.navigate(NavigationRouters.LOGIN)
                navHostController.popBackStack(NavigationRouters.LOGIN,false)
            })
        }
        Spacer(modifier = Modifier.padding(top = 15.dp))
    }
}






@Preview
@Composable
fun upperPrev() {
//    getStarted()
}