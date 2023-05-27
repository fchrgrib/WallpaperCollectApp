package com.example.wallpapercollect.presentation.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpapercollect.presentation.ui.theme.gray200

@Composable
fun boxContent(startText :String, endText:String) {
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