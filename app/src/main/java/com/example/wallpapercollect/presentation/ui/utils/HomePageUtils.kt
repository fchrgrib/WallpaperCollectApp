package com.example.wallpapercollect.presentation.ui.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun cardPhoto() {
    Card(modifier = Modifier
        .width(172.1.dp)
        .height(301.4.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        // TODO fill image in this card and favorite button
    }
}

@Preview
@Composable
fun prevCardPhoto() {
    Row(horizontalArrangement = Arrangement.Center,
    modifier = Modifier
        .fillMaxWidth()
        .padding(top = 17.dp)) {
         cardPhoto()
        Spacer(modifier = Modifier.padding(start = 15.dp))
        cardPhoto()
    }

}