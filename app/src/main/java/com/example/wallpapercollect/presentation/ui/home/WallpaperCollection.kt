package com.example.wallpapercollect.presentation.ui.home


import android.annotation.SuppressLint
import android.webkit.CookieManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallpapercollect.R
import com.example.wallpapercollect.api.models.ImagesCollections
import com.example.wallpapercollect.api.models.UrlAndId
import com.example.wallpapercollect.presentation.ui.models.NavigationDrawerMenuItem
import com.example.wallpapercollect.presentation.ui.navigation.DrawerBody
import com.example.wallpapercollect.presentation.ui.navigation.DrawerHeader
import com.example.wallpapercollect.presentation.ui.navigation.NavigationRouters
import com.example.wallpapercollect.presentation.ui.theme.brand300
import com.example.wallpapercollect.presentation.ui.utils.AppBar
import com.example.wallpapercollect.presentation.ui.utils.CardPhoto
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


@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WallpaperCollectionScreen(
    wallpaperCollect : WallpaperCollectUser = hiltViewModel(),
    profile: Profile = hiltViewModel(),
    navController: NavController,
    gsc: GoogleSignInClient
) {
    val context = LocalContext.current
    val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(context)

    if (isFirstTimeUserToWallpaper(context)) {
        manipulateActivityUserToWallpaper(context, false)
        profile.getProfileInfo()
        wallpaperCollect.getWallpaperCollection()
    }
    DisposableEffect(Unit) {

        onDispose {
            manipulateActivityUserToWallpaper(context, true)
        }
    }

    
    
    val isLoading = wallpaperCollect.isLoading.collectAsState(true).value
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val state = rememberPullRefreshState(refreshing = isLoading, onRefresh = { wallpaperCollect.getWallpaperCollection() })

    val statusWallpaperCollectUser = wallpaperCollect.wallpaperCollection.collectAsState(ImagesCollections(listOf(),"")).value
    val profileInfo = profile.profileInfo.collectAsState().value


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
            modifier = Modifier.pullRefresh(state)
        )
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
                    imageId = it.imageId
                )
            }
        }
    )

}