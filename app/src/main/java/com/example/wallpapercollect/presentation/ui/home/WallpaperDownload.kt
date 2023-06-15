package com.example.wallpapercollect.presentation.ui.home

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.wallpapercollect.R
import com.example.wallpapercollect.api.ApiConstants
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.presentation.ui.navigation.NavigationRouters
import com.example.wallpapercollect.presentation.ui.theme.brand500
import com.example.wallpapercollect.presentation.ui.utils.Downloader
import com.example.wallpapercollect.presentation.viewmodel.wallpaperpage.WallpaperCollectUser
import kotlinx.coroutines.flow.MutableStateFlow


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DownloadScreen(
    id:String,
    imageName:String,
    navController: NavController,
    wallpaperCollectUser: WallpaperCollectUser = hiltViewModel()
) {

    val statusWallpaperDelete = wallpaperCollectUser.wallpaperDeleteStatus.collectAsState(Status("")).value

    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }
    val isDeleteWallpaper:MutableState<Boolean?> = remember { mutableStateOf(null) }

    Scaffold(
        topBar = {
            Row(horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.back_button),
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { navController.popBackStack() }
                        .background(Color.Transparent)
                        .alpha(0.5f),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.trash_bold),
                    contentDescription = "Trash",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { openDialog.value = true }
                        ,
                    tint = Color.White
                )
            }
        },
        content = { DownloadBody(
            {Downloader(context).downloadFile("${ApiConstants.BASE_URL}images/$id/",imageName)},
            id
        ) }
    )

    if(openDialog.value){
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
                isDeleteWallpaper.value = null
                               },
            text = { Text(text = "Do you want to delete this wallpaper?")},
            confirmButton = {
                            TextButton(onClick = {
                                openDialog.value = false
                                isDeleteWallpaper.value = true
                            }) {
                                Text(text = "yes")
                            }
            },
            dismissButton = {
                TextButton(onClick = {
                    openDialog.value = false
                    isDeleteWallpaper.value = false
                }) {
                    Text(text = "no")
                }
            }
        )
    }

    if (isDeleteWallpaper.value == true){
        isDeleteWallpaper.value = null

        wallpaperCollectUser.wallpaperDelete(id)
    }

    if (statusWallpaperDelete.status=="ok"){
        Toast.makeText(context,"wallpaper deleted",Toast.LENGTH_LONG).show()
        wallpaperCollectUser.wallpaperDeleteStatus = MutableStateFlow(Status(""))

        navController.navigate(NavigationRouters.WALLPAPER){
            popUpTo(NavigationRouters.WALLPAPER+"/"+id+"/"+imageName){inclusive = true}
        }
    }
}

@Composable
fun DownloadBody(
    onClickDownload : () -> Unit,
    id: String
) {

    var isClicked:Boolean by rememberSaveable { mutableStateOf(false) }




    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black, RoundedCornerShape(0))
    ){


        AsyncImage(
            model = "${ApiConstants.BASE_URL}images/$id/",
            contentDescription = "wallpaper $id",
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
                    .background(brand500.copy(alpha = 0.8f), RoundedCornerShape(10.dp)),
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
                painter = if (isClicked) painterResource(id = R.drawable.red_heart) else painterResource(id = R.drawable.heart),
                contentDescription = "favorite",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 90.dp)
                    .clickable(onClick = { isClicked = !isClicked }),
                tint = Color.Unspecified
            )
        }
    }
}


