package com.example.wallpapercollect.presentation.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.wallpapercollect.R
import com.example.wallpapercollect.presentation.ui.theme.gray10
import com.example.wallpapercollect.presentation.ui.theme.gray100
import com.example.wallpapercollect.presentation.ui.theme.gray200
import com.example.wallpapercollect.presentation.ui.theme.interFont

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
        contentScale = ContentScale.Crop
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
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun TextFieldProfile(
     placeHolder:String,
     content : (String) -> Unit,
     firstContent:String
) {
    var text by rememberSaveable {
        mutableStateOf(firstContent)
    }

    TextField(
        value = text,
        onValueChange = {
            text = it
            content(text)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(1.dp, gray10, RoundedCornerShape(10.dp)),
        placeholder = {
            Text(
                text = placeHolder,
                fontFamily = interFont,
                fontWeight = FontWeight.Normal,
                color = gray100,
                fontSize = 12.sp
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFF7F8F9),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledTextColor = gray200
        ),
        shape = RoundedCornerShape(10.dp)
    )
}