package com.example.wallpapercollect.presentation.ui.home


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.presentation.ui.theme.brand500
import com.example.wallpapercollect.presentation.ui.utils.BoxContent
import com.example.wallpapercollect.presentation.ui.utils.PhotoProfileCustom
import com.example.wallpapercollect.presentation.ui.utils.PhotoProfileDefault
import com.example.wallpapercollect.presentation.ui.utils.emailSharedPreference
import com.example.wallpapercollect.presentation.ui.utils.getFileFromUri
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
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody


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
            profile = profile,
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
    profile: Profile = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    var imagePart by rememberSaveable { mutableStateOf<MultipartBody.Part?>(null) }
    var isCameraButtonClicked by rememberSaveable { mutableStateOf(false) }
    var isCallRequest  by rememberSaveable { mutableStateOf(false) }
    var isInitialUpload  by rememberSaveable { mutableStateOf(false) }
    val isUploadPhotoProfileCompleted = profile.isUploadPhotoProfileCompleted.collectAsState(false).value
    val photoProfileInitialUpload = profile.photoProfileUploadStatus.collectAsState(Status("")).value
    val photoProfileUpdate = profile.photoProfileUpdateStatus.collectAsState(Status("")).value



    if(userName!=""&& userNameSharedPreference(context)!= userName) manipulateUserNameSharedPreference(context,userName)
    if(photoProfile!=""&& photoProfileSharedPreference(context) != photoProfile) manipulatePhotoProfileSharedPreference(context,photoProfile)
    if(phoneNumber!=""&& phoneNumberSharedPreference(context) != phoneNumber) manipulatePhoneNumberSharedPreference(context,phoneNumber)
    if(email!=""&& emailSharedPreference(context) != email) manipulateEmailSharedPreference(context, email)


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            if (it.resultCode==Activity.RESULT_OK){

                val imageUri = it.data?.data!!
                val contentResolver = context.contentResolver
                val cacheDir = context.cacheDir
                var fileName = ""
                val cursor = contentResolver.query(imageUri,null, null, null, null)

                cursor?.use {curs->
                    if (curs.moveToFirst()){
                        val displayNameIndex = curs.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        if (displayNameIndex != -1) fileName = curs.getString(displayNameIndex)
                    }
                }

                val imageFile = getFileFromUri(contentResolver,imageUri,cacheDir)

                if (imageFile.exists()){
                    val requestBody = imageFile.asRequestBody("image/*".toMediaType())
                    imagePart = MultipartBody.Part.createFormData("Image", fileName, requestBody)

                    isCameraButtonClicked = true
                }
            }else{
                Toast.makeText(context,"Try again",Toast.LENGTH_LONG).show()
            }
        }
    )



    Column(modifier = Modifier.fillMaxSize()) {
        TopPartOfProfile(
            photoProfile = photoProfile,
            userName = userName,
            onClickPhoto = { /*TODO onClickPhoto*/ Log.d("photo", "clicked") },
            onClickCameraIcon = {
                                launcher.launch(
                                    Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT)
                                )
            },
            onClickEdit = {/*TODO edit*/},
            isAuthor = isAuthor
        )
        BottomPartOfProfile(
            phoneNumber = phoneNumber,
            email = email,
            location = location,
            isAuthor = isAuthor
        ) {/*TODO make onCLick delete account*/ }
    }

    if (isCameraButtonClicked){
        isCameraButtonClicked = false
        isCallRequest = true

        if(photoProfile=="") {
            isInitialUpload = true
            profile.uploadPhotoProfile(imagePart!!)
        }else{
            isInitialUpload = false
            profile.updatePhotoProfile(imagePart!!)
        }
    }

    if (isInitialUpload) {
        imagePart = null
        if (
            isCallRequest &&
            isUploadPhotoProfileCompleted &&
            photoProfileInitialUpload.status == "ok"
        ) {
            isCallRequest = false

            profile.getProfileInfo()
            Toast.makeText(context, "Photo Profile Changed", Toast.LENGTH_LONG).show()
        }
    }else{
        if (
            isCallRequest &&
            isUploadPhotoProfileCompleted &&
            photoProfileUpdate.status == "ok"
        ) {
            isCallRequest = false

            profile.getProfileInfo()
            Toast.makeText(context, "Photo Profile Changed", Toast.LENGTH_LONG).show()
        }
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

                    if (photoProfile != "") PhotoProfileCustom(photoProfile = photoProfile, onClickPhoto = onClickPhoto,isAuthor = isAuthor)
                    else PhotoProfileDefault(onClickPhoto = onClickPhoto, isAuthor = isAuthor)
                    if(!isAuthor) {
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
                }
                Spacer(modifier = Modifier.padding(top = 12.dp))
                Row {
                    Text(
                        text = userName,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.padding(7.dp))
                    if (!isAuthor) {
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

}