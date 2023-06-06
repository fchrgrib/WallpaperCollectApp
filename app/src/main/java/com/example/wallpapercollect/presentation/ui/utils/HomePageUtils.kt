package com.example.wallpapercollect.presentation.ui.utils

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.wallpapercollect.R
import com.example.wallpapercollect.presentation.ui.navigation.NavigationRouters


@Composable
fun CardPhoto(navController: NavController,imageUrl:String,imageId:String) {
    Card(modifier = Modifier
        .width(172.1.dp)
        .height(301.4.dp)
        .padding(4.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        var isClicked by rememberSaveable { mutableStateOf(false) }

        val imageLoader = ImageLoader.Builder(LocalContext.current)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "wallpaper",

                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxSize()
                    .clickable { navController.navigate(NavigationRouters.WALLPAPER + "/" + imageId) },
                contentScale = ContentScale.Crop,
                placeholder = rememberAsyncImagePainter(
                    model =
                    ImageRequest.Builder(LocalContext.current).data(R.drawable.loading_image)
                        .apply(block = {
                            size(6)
                        }).build(), imageLoader = imageLoader
                )
            )
            Icon(
                painter = if (isClicked) painterResource(id = R.drawable.red_heart) else painterResource(
                    id = R.drawable.heart
                ),
                contentDescription = "heart",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 2.dp, bottom = 2.dp)
                    .clickable {
                        isClicked = !isClicked
                    },
                tint = Color.Unspecified
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit
) {
    TopAppBar(
        title = {
            Row(horizontalArrangement = Arrangement.Center) {
                Icon(
                    painter = painterResource(id = R.drawable.walper_logo),
                    contentDescription = "logo walper",
                    modifier = Modifier.weight(7f),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector =Icons.Default.Menu, contentDescription = null)
            }
        }
    )
}

@Preview
@Composable
fun AppBarPrev() {
    AppBar {

    }
}
