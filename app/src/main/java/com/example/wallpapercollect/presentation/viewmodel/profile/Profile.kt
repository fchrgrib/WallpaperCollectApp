package com.example.wallpapercollect.presentation.viewmodel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.UserDescription
import com.example.wallpapercollect.repository.WallpaperCollectRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Profile @Inject constructor(
    private val wallpaperCollectRepoImpl: WallpaperCollectRepoImpl
): ViewModel() {

    private val _profileInfo = MutableStateFlow(UserDescription("","","","",""))

    val profileInfo = _profileInfo

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
}