package com.example.wallpapercollect.presentation.viewmodel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.UserDescription
import com.example.wallpapercollect.api.models.UserUpdate
import com.example.wallpapercollect.repository.WallpaperCollectRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject


@HiltViewModel
class Profile @Inject constructor(
    private val wallpaperCollectRepoImpl: WallpaperCollectRepoImpl
): ViewModel() {

    private val _profileInfo = MutableStateFlow(UserDescription("","","","",""))
    private val _photoProfileUploadStatus = MutableStateFlow(Status(""))
    private val _photoProfileUpdateStatus = MutableStateFlow(Status(""))
    private val _descProfileUpdateStatus = MutableStateFlow(Status(""))

    val profileInfo = _profileInfo
    val photoProfileUploadStatus = _photoProfileUploadStatus
    val photoProfileUpdateStatus = _photoProfileUpdateStatus
    val descProfileUpdateStatus = _descProfileUpdateStatus
    val isUploadPhotoProfileCompleted = MutableStateFlow(false)
    val isUpdateProfileDescCompleted = MutableStateFlow(false)


    fun getProfileInfo(){
        viewModelScope.launch {
            try {
                val response = wallpaperCollectRepoImpl.profile()
                _profileInfo.emit(response)
            }catch (e :Exception){
                _profileInfo.emit(UserDescription("","","","",e.message.toString()))
            }
        }
    }

    fun uploadPhotoProfile(image :MultipartBody.Part){
        viewModelScope.launch {
            isUploadPhotoProfileCompleted.emit(false)
            try {
                val response = wallpaperCollectRepoImpl.profilePictureUpload(image)
                _photoProfileUploadStatus.emit(response)
            }catch (e :Exception){
                _photoProfileUploadStatus.emit(Status(e.message.toString()))
            }
            isUploadPhotoProfileCompleted.emit(true)
        }
    }

    fun updatePhotoProfile(image :MultipartBody.Part){
        viewModelScope.launch {
            isUploadPhotoProfileCompleted.emit(false)
            try {
                val response = wallpaperCollectRepoImpl.profilePictureUpdate(image)
                _photoProfileUpdateStatus.emit(response)
            }catch (e :Exception){
                _photoProfileUpdateStatus.emit(Status(e.message.toString()))
            }
            isUploadPhotoProfileCompleted.emit(true)
        }
    }

    fun updateProfileDesc(userUpdate: UserUpdate){
        viewModelScope.launch {
            isUpdateProfileDescCompleted.emit(false)
            try {
                val response = wallpaperCollectRepoImpl.updateProfileDesc(userUpdate)
                _descProfileUpdateStatus.emit(response)
            }catch (e:Exception){
                _descProfileUpdateStatus.emit(Status(e.message.toString()))
            }
            isUpdateProfileDescCompleted.emit(true)
        }
    }
}