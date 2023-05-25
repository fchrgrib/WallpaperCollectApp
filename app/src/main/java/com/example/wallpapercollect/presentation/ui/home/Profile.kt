package com.example.wallpapercollect.presentation.ui.home

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wallpapercollect.R
import com.example.wallpapercollect.presentation.ui.theme.brand500


@Composable
fun profileBody() {
    
}


@Composable
fun backgroundProfile() {
    Box(modifier = Modifier.fillMaxWidth().height(168.dp).background(brand500,
        RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
    ))
}

@Preview
@Composable
fun prevBackgroundProfile() {
    backgroundProfile()
}