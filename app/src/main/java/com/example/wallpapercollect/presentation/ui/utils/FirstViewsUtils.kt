package com.example.wallpapercollect.presentation.ui.utils

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpapercollect.R
import com.example.wallpapercollect.presentation.ui.theme.brand500
import com.example.wallpapercollect.presentation.ui.theme.gray10
import com.example.wallpapercollect.presentation.ui.theme.gray100
import com.example.wallpapercollect.presentation.ui.theme.gray200
import com.example.wallpapercollect.presentation.ui.theme.interFont

@Composable
fun textHeaderLogRes(header :String, description:String) {
    Text(
        text = header,
        fontFamily = interFont,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Text(
        text = description,
        fontFamily = interFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = Color(0xFF979797)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldLogRes(placeHolder:String, content : (String) -> Unit) {
    var text by rememberSaveable {
        mutableStateOf("")
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
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFF7F8F9),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = gray200
        ),
        shape = RoundedCornerShape(10.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldLogResPass(placeHolder:String, content : (String) -> Unit) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    var isVisible by rememberSaveable {
        mutableStateOf(true)
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
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFF7F8F9),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = gray200
        ),
        shape = RoundedCornerShape(10.dp),
        visualTransformation = if (isVisible) PasswordVisualTransformation('*') else VisualTransformation.None,
        trailingIcon = { Icon(
            painter = painterResource(id = R.drawable.fluent_eye_20_filled),
            contentDescription = "visibility",
            modifier = Modifier.clickable { isVisible = !isVisible },
            tint = Color.Unspecified
        )}
    )
}


@Composable
fun logResTripButton(
    icon: Int,
    colorIcon: Color,
    colorButton:Color,
    colorText:Color,
    colorBorder: Color,
    nameIcon:String,
    textButton: String,
    todo: () -> Unit

) {
    Button(
        onClick = todo,
        colors = ButtonDefaults.buttonColors(containerColor = colorButton),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(1.dp, colorBorder, RoundedCornerShape(10.dp)),
        shape = RoundedCornerShape(10.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = nameIcon,
                tint = colorIcon,
                modifier = Modifier.weight(2f)
            )

            Text(
                text = textButton,
                color = colorText,
                fontFamily = interFont,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(5f)
            )
        }
    }
    Spacer(modifier = Modifier.padding(top = 12.dp))
}

@Composable
fun logResButton(textButton:String, onClickable:()->Unit) {
    Button(
        onClick = onClickable,
        colors = ButtonDefaults.buttonColors(
            containerColor = brand500
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(1.dp, brand500, RoundedCornerShape(10.dp))
    ) {
        Text(
            text = textButton,
            fontFamily = interFont,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}