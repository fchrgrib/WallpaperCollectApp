package com.example.wallpapercollect.presentation.ui.home


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.provider.OpenableColumns
import android.webkit.CookieManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallpapercollect.R
import com.example.wallpapercollect.api.models.ImagesCollections
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.UrlAndId
import com.example.wallpapercollect.presentation.ui.models.NavigationDrawerMenuItem
import com.example.wallpapercollect.presentation.ui.navigation.DrawerBody
import com.example.wallpapercollect.presentation.ui.navigation.DrawerHeader
import com.example.wallpapercollect.presentation.ui.navigation.NavigationRouters
import com.example.wallpapercollect.presentation.ui.theme.brand300
import com.example.wallpapercollect.presentation.ui.utils.AppBar
import com.example.wallpapercollect.presentation.ui.utils.CardPhoto
import com.example.wallpapercollect.presentation.ui.utils.getFileFromUri
import com.example.wallpapercollect.presentation.ui.utils.isFirstTimeUserToWallpaper
import com.example.wallpapercollect.presentation.ui.utils.manipulateActivityUserToWallpaper
import com.example.wallpapercollect.presentation.viewmodel.profile.Profile
import com.example.wallpapercollect.presentation.viewmodel.wallpaperpage.WallpaperCollectUser
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun WallpaperCollectionScreen(
    wallpaperCollect : WallpaperCollectUser = hiltViewModel(),
    profile: Profile = hiltViewModel(),
    navController: NavController,
    gsc: GoogleSignInClient
) {


    val context = LocalContext.current
    val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(context)

    var isImageFABClicked:Boolean by rememberSaveable { mutableStateOf(false) }
    var isUploadRequestCalled:Boolean by rememberSaveable { mutableStateOf(false) }
    var imagePart by rememberSaveable {mutableStateOf<MultipartBody.Part?>(null)}

    val isLoading = wallpaperCollect.isLoading.collectAsState(false).value
    val isUploadCompleted = wallpaperCollect.isUploadCompleted.collectAsState(false).value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val state = rememberPullRefreshState(refreshing = isLoading, onRefresh = { wallpaperCollect.getWallpaperCollection() })


    val statusWallpaperCollectUser = wallpaperCollect.wallpaperCollection.collectAsState(ImagesCollections(listOf(),"")).value
    val statusImageUpload = wallpaperCollect.wallpaperUploadStatus.collectAsState(Status("")).value
    val profileInfo = profile.profileInfo.collectAsState().value

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {

            if (it.resultCode == Activity.RESULT_OK){

                val imageUri = it.data?.data!!
                val contentResolver = context.contentResolver
                val cacheDir = context.cacheDir
                var fileName =""
                val cursor = contentResolver.query(imageUri, null, null, null, null)


                cursor?.use { curs ->
                    if (curs.moveToFirst()) {
                        val displayNameIndex = curs.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        if (displayNameIndex != -1) fileName = curs.getString(displayNameIndex)
                    }
                }


                val imageFile = getFileFromUri(contentResolver,imageUri,cacheDir)
                if (imageFile.exists()){
                    val requestBody = imageFile.asRequestBody("image/*".toMediaType())
                    imagePart = MultipartBody.Part.createFormData("Image", fileName, requestBody)

                    isImageFABClicked = true
                }
            }else{
                Toast.makeText(context,"Try again",Toast.LENGTH_LONG).show()
            }
        }
    )
    DisposableEffect(Unit) {
        onDispose {
            manipulateActivityUserToWallpaper(context, true)
        }
    }



    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { wallpaperCollect.getWallpaperCollection() },
        modifier = Modifier
        .fillMaxSize(),
        refreshTriggerDistance = 2.dp
        ) {


        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                AppBar {
                    scope.launch {
                        scaffoldState.drawerState.open()
                        profile.getProfileInfo()
                    }
                }
            },
            drawerBackgroundColor = brand300,
            drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
            drawerContent = {
                DrawerHeader(
                    imageUrl = profileInfo.photoProfile,
                    userName = profileInfo.userName,
                    navController = navController
                )
                DrawerBody(
                    items = listOf(
                        NavigationDrawerMenuItem(
                            id = "home",
                            title = "Home",
                            contentDescription = "home button",
                            icon = R.drawable.home
                        ),
                        NavigationDrawerMenuItem(
                            id = "privacy",
                            title = "Privacy Policy",
                            contentDescription = "privacy policy button",
                            icon = R.drawable.document_text
                        ),
                        NavigationDrawerMenuItem(
                            id = "author",
                            title = "Author's contact",
                            contentDescription = "author contact button",
                            icon = R.drawable.call
                        ),
                        NavigationDrawerMenuItem(
                            id = "logout",
                            title = "Logout",
                            contentDescription = "logout button",
                            icon = R.drawable.logout
                        )
                    ),
                    onClickItem = {
                        when (it.id) {
                            "home" -> {
                                navController.navigate(NavigationRouters.WALLPAPER)
                                manipulateActivityUserToWallpaper(context, true)
                            }

                            "privacy" -> {
                                navController.navigate(NavigationRouters.PRIVACY)
                                manipulateActivityUserToWallpaper(context, true)
                            }

                            "author" -> {
                                navController.navigate(NavigationRouters.AUTHOR)
                                manipulateActivityUserToWallpaper(context, true)
                            }

                            "logout" -> {
                                if (account != null) gsc.signOut()
                                CookieManager.getInstance().removeAllCookie()
                                navController.navigate(NavigationRouters.LOGIN) {
                                    popUpTo(NavigationRouters.WALLPAPER) { inclusive = true }
                                }
                            }

                            else -> {}
                        }
                    }
                )

            },
            content = {
                if(statusWallpaperCollectUser.status == "ok") {
                    WallpaperBody(
                        navController = navController,
                        imageData = statusWallpaperCollectUser.wallpaperCollection
                    )
                }

            },
            modifier = Modifier.pullRefresh(state),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                                val intent = Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT)
                                launcher.launch(intent)
                              },
                    shape = CircleShape,
                    contentColor = Color.White,
                    containerColor = brand300,
                    modifier = Modifier.size(70.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "add")
                }
            },
            floatingActionButtonPosition = FabPosition.End
        )
    }

    if (isImageFABClicked&&imagePart!=null){
        isImageFABClicked = false
        isUploadRequestCalled = true

        wallpaperCollect.wallpaperUpload(imagePart!!)
        return
    }
    if (isUploadRequestCalled&&statusImageUpload.status=="ok"&&isUploadCompleted){
        isUploadRequestCalled = false
        imagePart = null

        Toast.makeText(context,"Upload Success",Toast.LENGTH_LONG).show()
        wallpaperCollect.getWallpaperCollection()
        return
    }


    if (isFirstTimeUserToWallpaper(context)) {
        manipulateActivityUserToWallpaper(context, false)

        profile.getProfileInfo()
        wallpaperCollect.getWallpaperCollection()
    }



}



@Composable
fun WallpaperBody(navController: NavController, imageData : List<UrlAndId>) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 2.dp,
        content = {
            items(imageData){
                CardPhoto(
                    navController = navController,
                    imageUrl = it.imageUrls +"",
                    imageId = it.imageId,
                    imageName = it.imageName
                )
            }
        }
    )

}

