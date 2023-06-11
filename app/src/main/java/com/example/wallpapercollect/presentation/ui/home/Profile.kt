package com.example.wallpapercollect.presentation.ui.home


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wallpapercollect.R
import com.example.wallpapercollect.presentation.ui.theme.brand500
import com.example.wallpapercollect.presentation.ui.utils.BoxContent
import com.example.wallpapercollect.presentation.ui.utils.PhotoProfileCustom
import com.example.wallpapercollect.presentation.ui.utils.PhotoProfileDefault
import com.example.wallpapercollect.presentation.ui.utils.emailSharedPreference
import com.example.wallpapercollect.presentation.ui.utils.isFirstTimeUserToProfile
import com.example.wallpapercollect.presentation.ui.utils.manipulateActivityUserToProfile
import com.example.wallpapercollect.presentation.ui.utils.manipulateEmailSharedPreference
import com.example.wallpapercollect.presentation.ui.utils.manipulatePhoneNumberSharedPreference
import com.example.wallpapercollect.presentation.ui.utils.manipulatePhotoProfileSharedPreference
import com.example.wallpapercollect.presentation.ui.utils.manipulateUserNameSharedPreference
import com.example.wallpapercollect.presentation.ui.utils.phoneNumberSharedPreference
import com.example.wallpapercollect.presentation.ui.utils.photoProfileSharedPreference
import com.example.wallpapercollect.presentation.ui.utils.userNameSharedPreference
import com.example.wallpapercollect.presentation.viewmodel.profile.Profile


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenProfile(
    navController: NavController,
    profile: Profile = hiltViewModel()
) {
    val context = LocalContext.current
    val profileInfo = profile.profileInfo.collectAsState().value

    if(isFirstTimeUserToProfile(context)){
        manipulateActivityUserToProfile(context,false)
        profile.getProfileInfo()
    }
    DisposableEffect(Unit) {

        onDispose {
            manipulateActivityUserToProfile(context, true)
        }
    }

    Scaffold(
        topBar = {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.back_button),
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            navController.popBackStack()
                            profile.getProfileInfo()
                            manipulateActivityUserToProfile(context, true)
                        },
                    tint = Color.Unspecified
                )
            }
        },
        content = { ProfileBody(
            userName = profileInfo.userName,
            email = profileInfo.email,
            phoneNumber = profileInfo.phoneNumber,
            photoProfile = profileInfo.photoProfile,
            location = "Indonesian",
            isAuthor = false,
            navController = navController
        ) }
    )

}

@Composable
fun ProfileBody(
    userName: String,
    photoProfile: String,
    phoneNumber: String,
    email: String,
    location: String,
    isAuthor :Boolean,
    navController: NavController
) {
    val context = LocalContext.current


    if(userName!=""&& userNameSharedPreference(context)!= userName) manipulateUserNameSharedPreference(context,userName)
    if(photoProfile!=""&& photoProfileSharedPreference(context) != photoProfile) manipulatePhotoProfileSharedPreference(context,photoProfile)
    if(phoneNumber!=""&& phoneNumberSharedPreference(context) != phoneNumber) manipulatePhoneNumberSharedPreference(context,phoneNumber)
    if(email!=""&& emailSharedPreference(context) != email) manipulateEmailSharedPreference(context, email)


    Column(modifier = Modifier.fillMaxSize()) {
        TopPartOfProfile(
            photoProfile = photoProfileSharedPreference(context),
            userName = userNameSharedPreference(context),
            onClickPhoto = { /*TODO onClickPhoto*/ Log.d("photo", "clicked") },
            onClickCameraIcon = { /*TODO onClickCamera*/ },
            onClickEdit = {/*TODO edit*/},
            isAuthor = isAuthor
        )
        BottomPartOfProfile(
            phoneNumber = phoneNumberSharedPreference(context),
            email = emailSharedPreference(context),
            location = location,
            isAuthor = isAuthor
        ) {/*TODO make onCLick delete account*/ }
    }
}

@Composable
fun BottomPartOfProfile(
    phoneNumber :String,
    email :String,
    location :String,
    isAuthor: Boolean,
    onClickDeleteAccount : () -> Unit
) {
    Column(modifier = Modifier
        .padding(horizontal = 24.dp)
        .fillMaxSize()) {
        BoxContent(startText = "Phone", endText = phoneNumber)
        BoxContent(startText = "Email", endText = email)
        BoxContent(startText = "Location", endText = location)
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 34.dp)
        ) {
            if (!isAuthor) {
                Button(
                    onClick = onClickDeleteAccount,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF8092)
                    )

                ) {
                    Text(
                        text = "Delete Account",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}



@Composable
fun TopPartOfProfile(
    photoProfile : String,
    userName : String,
    onClickPhoto : () -> Unit,
    onClickCameraIcon: () -> Unit,
    onClickEdit : () -> Unit,
    isAuthor: Boolean
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(168.dp)
                    .background(
                        brand500,
                        RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(0)
                    )
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 100.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box {

                    if (photoProfile != "") PhotoProfileCustom(photoProfile = photoProfile, onClickPhoto = onClickPhoto)
                    else PhotoProfileDefault(onClickPhoto = onClickPhoto, isAuthor = isAuthor)

                    Icon(
                        painter = painterResource(id = R.drawable.camera),
                        contentDescription = "camera",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .offset(x = 78.dp, y = 78.dp)
                            .clickable(
                                indication = null,
                                onClick = onClickCameraIcon,
                                interactionSource = interactionSource
                            )
                    )
                }
                Spacer(modifier = Modifier.padding(top = 12.dp))
                Row {
                    Text(
                        text = userName,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.padding(7.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "edit button",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickable(
                                interactionSource = interactionSource,
                                onClick = onClickEdit,
                                indication = null
                            )
                    )
                }
            }

        }
    }

}