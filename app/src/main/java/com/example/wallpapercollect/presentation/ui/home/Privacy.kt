package com.example.wallpapercollect.presentation.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wallpapercollect.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrivacyScreen(
    navController :NavController
) {
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
        content = {
            Image(
                painter = painterResource(id = R.drawable.privacy_policy),
                contentDescription = "privacy",
                modifier = Modifier.fillMaxSize()
            )
        }
    )
}