package com.example.wallpapercollect.presentation.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpapercollect.R
import com.example.wallpapercollect.presentation.ui.theme.brand500


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DownloadScreen(id : String) {
    Scaffold(
        topBar = {
            Row(horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.back_button),
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {/*TODO Implement back action here*/ }
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
            {/*TODO make download API*/},
            {/*TODO make favourite API*/}
        ) }
    )
}

@Composable
fun DownloadBody(
    onClickDownload : () -> Unit,
    onClickFavourite : () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black, RoundedCornerShape(0))
    ){

        Image(
            painter = painterResource(id = R.drawable.ini_wallpaper),
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
                painter = painterResource(id = R.drawable.heart),
                contentDescription = "favorite",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 90.dp)
                    .clickable(onClick = onClickFavourite),
                tint = Color.Unspecified
            )
        }
    }
}

@Preview
@Composable
fun prevDownloadBody() {
    DownloadBody(
        { Log.d("download-button", "clicked")},
        {Log.d("favourite-button", "clicked")}
    )
}