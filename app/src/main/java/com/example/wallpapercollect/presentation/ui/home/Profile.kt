package com.example.wallpapercollect.presentation.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpapercollect.R
import com.example.wallpapercollect.presentation.ui.firstviews.getstarted.body
import com.example.wallpapercollect.presentation.ui.theme.brand500
import com.example.wallpapercollect.presentation.ui.utils.boxContent


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun screenProfile() {
    Scaffold(
        topBar = {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.back_button),
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            // TODO Implement back action here
                        },
                    tint = Color.Unspecified
                )
            }
        },
        content = { profileBody() }
    )
}

@Preview
@Composable
fun prevScreenProfile() {
    screenProfile()
}
@Composable
fun profileBody() {
    Column(modifier = Modifier.fillMaxSize()) {
        topPartOfProfile(
            photoProfile = R.drawable.foto_gue,
            userName = "Fahrian Afdholi",
            onClickPhoto = { /*TODO onClickPhoto*/ },
            onClickCameraIcon = { /*TODO onClickCamera*/ },
            onClickEdit = {/*TODO edit*/}
        )
        bottomPartOfProfile(
            "+6287724273282",
            "Fahrian.Afdholi@gmail.com",
            "Indonesia"
        ) {/*TODO make onCLick delete account*/ }
    }
}

@Preview
@Composable
fun prevProfileBody() {
    profileBody()
}

@Composable
fun bottomPartOfProfile(
    phoneNumber :String,
    email :String,
    location :String,
    onClickDeleteAccount : () -> Unit
) {
    Column(modifier = Modifier
        .padding(horizontal = 24.dp)
        .fillMaxSize()) {
        boxContent(startText = "Phone", endText = phoneNumber)
        boxContent(startText = "Email", endText = email)
        boxContent(startText = "Location", endText = location)
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 34.dp)
        ) {

            Button(
                onClick = { onClickDeleteAccount },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF8092)
                )

            ) {
                Text(
                    text = "Delete Account",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun prevBottomPartProfile() {
    bottomPartOfProfile(
        "+6287724273282",
        "Fahrian.Afdholi@gmail.com",
        "Indonesia"
    ) { }
}

@Composable
fun topPartOfProfile(
    photoProfile : Int,
    userName : String,
    onClickPhoto : () -> Unit,
    onClickCameraIcon: () -> Unit,
    onClickEdit : () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(168.dp)
                    .background(
                        brand500,
                        RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(0)
                    )
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 100.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box {
                    Image(
                        painter = painterResource(id = photoProfile),
                        modifier = Modifier
                            .height(106.dp)
                            .width(106.dp)
                            .clip(CircleShape)
                            .clickable { onClickPhoto },
                        contentDescription = "photo profile",
                        contentScale = ContentScale.Fit
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = "camera",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .offset(x = 78.dp, y = 78.dp)
                            .clickable(
                                indication = null,
                                onClick = {onClickCameraIcon},
                                interactionSource = interactionSource
                            )
                    )
                }
                Spacer(modifier = Modifier.padding(top = 12.dp))
                Row {
                    Text(
                        text = userName,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.padding(7.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "edit button",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickable { onClickEdit }
                            .clickable(
                                interactionSource = interactionSource,
                                onClick = {onClickEdit},
                                indication = null
                            )
                    )
                }
            }

        }
    }

}

@Preview
@Composable
fun prevBackgroundProfile() {
    topPartOfProfile(
        R.drawable.foto_gue,
        "Fahrian Afdholi",
        {},
        {},
        {}
    )
}