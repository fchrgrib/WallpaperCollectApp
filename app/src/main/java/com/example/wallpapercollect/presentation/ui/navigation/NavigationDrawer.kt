package com.example.wallpapercollect.presentation.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wallpapercollect.presentation.ui.models.NavigationDrawerMenuItem
import com.example.wallpapercollect.presentation.ui.utils.PhotoProfileCustom
import com.example.wallpapercollect.presentation.ui.utils.PhotoProfileDefault
import com.example.wallpapercollect.presentation.ui.utils.manipulateActivityUserToProfile
import com.example.wallpapercollect.presentation.ui.utils.manipulateActivityUserToWallpaper


@Composable
fun DrawerHeader(
    imageUrl: String,
    userName:String,
    navController: NavController
) {
    val context = LocalContext.current

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 72.dp)){
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(NavigationRouters.PROFILE)
                manipulateActivityUserToWallpaper(context, true)
                manipulateActivityUserToProfile(context, true)
            }
            .padding(24.dp)) {

            if(imageUrl != "") PhotoProfileCustom(imageUrl = imageUrl)
            else PhotoProfileDefault()
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Text(text = userName,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.offset(y = 15.dp)
            )
        }
    }
    Spacer(modifier = Modifier.padding(vertical = 40.dp))
}

@Composable
fun DrawerBody(
    items: List<NavigationDrawerMenuItem>,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(
        color = Color.Black,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    onClickItem: (NavigationDrawerMenuItem) -> Unit
) {
    LazyColumn(modifier){
        items(items){ item->
            if (item.id == "logout") Spacer(modifier = Modifier.padding(top = 90.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { onClickItem(item) }
                .padding(24.dp)
            ) {
                Icon(painter = painterResource(id = item.icon), contentDescription = item.contentDescription)
                Spacer(modifier = Modifier.padding(horizontal = 16.dp))

                Text(text = item.title,
                    fontSize = textStyle.fontSize,
                    fontWeight = textStyle.fontWeight,
                    color = textStyle.color,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}