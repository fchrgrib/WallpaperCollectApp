package com.example.wallpapercollect.presentation.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.wallpapercollect.R
import com.example.wallpapercollect.api.ApiConstants
import com.example.wallpapercollect.presentation.ui.theme.brand500
import com.example.wallpapercollect.presentation.ui.utils.Downloader


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DownloadScreen(
    id:String,
    imageName:String,
    navController: NavController,
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Row(horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.back_button),
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { navController.popBackStack() }
                        .background(Color.Transparent)
                        .alpha(0.5f),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.info_circle),
                    contentDescription = "Info",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {/*TODO Implement Info*/ }
                        ,
                    tint = Color.Unspecified
                )
            }
        },
        content = { DownloadBody(
            {Downloader(context).downloadFile("${ApiConstants.BASE_URL}images/$id/",imageName)},
            id
        ) }
    )
}

@Composable
fun DownloadBody(
    onClickDownload : () -> Unit,
    id: String
) {

    var isClicked:Boolean by rememberSaveable { mutableStateOf(false) }
    val imageUrl:String by rememberSaveable { mutableStateOf("${ApiConstants.BASE_URL}images/$id/") }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black, RoundedCornerShape(0))
    ){


        AsyncImage(
            model = imageUrl,
            contentDescription = "wallpaper",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 34.dp)
                .padding(horizontal = 24.dp)
        ) {

            Button(
                onClick =  onClickDownload  ,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(brand500.copy(alpha = 0.5f), RoundedCornerShape(10.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )

            ) {
                Text(
                    text = "Download",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
            Icon(
                painter = if (isClicked) painterResource(id = R.drawable.full_heart) else painterResource(id = R.drawable.heart),
                contentDescription = "favorite",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 90.dp)
                    .clickable(onClick = { isClicked = !isClicked }),
                tint = Color.Unspecified
            )
        }
    }
}


