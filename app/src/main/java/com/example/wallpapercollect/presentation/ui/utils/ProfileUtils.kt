package com.example.wallpapercollect.presentation.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.wallpapercollect.R
import com.example.wallpapercollect.presentation.ui.theme.gray200

@Composable
fun BoxContent(startText :String, endText:String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(64.dp),
        contentAlignment = Alignment.CenterStart
    ){
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = startText,
                modifier = Modifier.weight(1f),
                fontSize = 16.sp,
                fontWeight =  FontWeight.Normal,
                color = Color.Black
            )
            Text(
                text = endText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = gray200
            )
        }

    }
}

@Composable
fun PhotoProfileDefault(
    onClickPhoto : () -> Unit,
    isAuthor:Boolean
) {
    Image(
        painter = if (isAuthor) painterResource(id = R.drawable.foto_gue)
        else painterResource(id = R.drawable.profile),
        modifier = Modifier
            .height(106.dp)
            .width(106.dp)
            .clip(CircleShape)
            .clickable(onClick = onClickPhoto),
        contentDescription = "photo profile",
        contentScale = ContentScale.Fit
    )
}

@Composable
fun PhotoProfileCustom(
    photoProfile :String,
    onClickPhoto: () -> Unit,
    isAuthor: Boolean
) {
    if (!isAuthor) {
        AsyncImage(
            model = photoProfile,
            modifier = Modifier
                .height(106.dp)
                .width(106.dp)
                .clip(CircleShape)
                .clickable(onClick = onClickPhoto),
            contentDescription = "photo profile",
            contentScale = ContentScale.Crop
        )
    }else{
        Image(
            painter = painterResource(id = R.drawable.foto_gue),
            modifier = Modifier
                .height(106.dp)
                .width(106.dp)
                .clip(CircleShape)
                .clickable(onClick = onClickPhoto),
            contentDescription = "photo profile",
            contentScale = ContentScale.Fit
        )
    }
}