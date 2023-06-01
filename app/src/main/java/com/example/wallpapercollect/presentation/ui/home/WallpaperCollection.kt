package com.example.wallpapercollect.presentation.ui.home


import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.wallpapercollect.presentation.ui.utils.AppBar
import com.example.wallpapercollect.presentation.ui.utils.CardPhoto
import com.example.wallpapercollect.presentation.viewmodel.profile.Profile
import com.example.wallpapercollect.presentation.viewmodel.wallpaperpage.WallpaperCollectUser
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WallpaperCollectionScreen(
    wallpaperCollect : WallpaperCollectUser = hiltViewModel(),
    profile: Profile = hiltViewModel(),
    navController: NavController
) {
    val isLoading by wallpaperCollect.isLoading.collectAsState()
    val refreshing = rememberSwipeRefreshState(isRefreshing = isLoading)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val statusWallpaperCollectUser = wallpaperCollect.wallpaperCollection.collectAsState(
        ImagesCollections(ArrayList(),"")
    ).value
    val profileInfo = profile.profileInfo.collectAsState().value

    SwipeRefresh(state = refreshing, onRefresh = wallpaperCollect::getWallpaperCollection) {

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { AppBar {
                scope.launch { scaffoldState.drawerState.open() }
            }},
            drawerGesturesEnabled = scaffoldState.drawerState.isClosed,
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
                        )
                    ),
                    onClickItem ={
                        when(it.title){
                            "home" -> navController.navigate(NavigationRouters.WALLPAPER)
                            "privacy" -> navController.navigate(NavigationRouters.PRIVACY)
                            "author" -> navController.navigate(NavigationRouters.AUTHOR)
                            else -> {}
                        }
                    }
                )

            },
            content = {
                WallpaperBody(navController = navController, imageData = statusWallpaperCollectUser.wallpaperCollection)
            }
        )

    }
}



@Composable
fun WallpaperBody(navController: NavController, imageData : ArrayList<UrlAndId>) {

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 2.dp,
        content = {
            items(imageData){
                CardPhoto(
                    navController = navController,
                    imageUrl = it.imageUrls,
                    imageId = it.imageId
                )
            }
        }
    )

}

//@Preview
//@Composable
//fun prevWalper() {
//    val dummy:ArrayList<String> = ArrayList()
//    dummy.add("https://images.unsplash.com/photo-1527769929977-c341ee9f2033?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80")
//    dummy.add("https://images.unsplash.com/photo-1527769929977-c341ee9f2033?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80")
//    dummy.add("https://images.unsplash.com/photo-1527769929977-c341ee9f2033?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80")
//    dummy.add("https://images.unsplash.com/photo-1527769929977-c341ee9f2033?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80")
//    dummy.add("https://images.unsplash.com/photo-1527769929977-c341ee9f2033?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80")
//    dummy.add("https://images.unsplash.com/photo-1527769929977-c341ee9f2033?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80")
//    dummy.add("https://images.unsplash.com/photo-1527769929977-c341ee9f2033?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80")
//    dummy.add("https://images.unsplash.com/photo-1527769929977-c341ee9f2033?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80")
//    dummy.add("https://images.unsplash.com/photo-1527769929977-c341ee9f2033?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80")
//    dummy.add("https://images.unsplash.com/photo-1527769929977-c341ee9f2033?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80")
//    dummy.add("https://images.unsplash.com/photo-1527769929977-c341ee9f2033?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80")
//    dummy.add("https://images.unsplash.com/photo-1527769929977-c341ee9f2033?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80")
//
//
//
//    Box(modifier = Modifier
//        .fillMaxSize()){
////        WallpaperBody(imageUrl = dummy)
//    }
//}