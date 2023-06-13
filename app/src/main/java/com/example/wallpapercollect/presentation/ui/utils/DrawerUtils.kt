package com.example.wallpapercollect.presentation.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.wallpapercollect.R

@Composable
fun PhotoProfileCustom(
    imageUrl:String
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "photo profile",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
    )
}

@Composable
fun PhotoProfileDefault() {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "photo profile",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
    )
}