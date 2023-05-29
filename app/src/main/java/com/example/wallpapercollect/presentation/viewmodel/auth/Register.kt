package com.example.wallpapercollect.presentation.viewmodel.auth


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.Url
import com.example.wallpapercollect.api.models.UserRegister
import com.example.wallpapercollect.repository.WallpaperCollectRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject


@HiltViewModel
class Register @Inject constructor(
    private val wallpaperCollectRepoImpl: WallpaperCollectRepoImpl
): ViewModel() {
    private val _registerEmailDefaultStatus = MutableStateFlow(Status(""))
    private val _registerGoogleSession = MutableStateFlow(Url(""))
    private val _registerFacebookSession = MutableStateFlow<Any?>(null)

    val registerEmailDefaultStatus: Flow<Status> = _registerEmailDefaultStatus
    val registerGoogleSession : Flow<Url> = _registerGoogleSession
    val registerFacebookSession: Flow<Any?> = _registerFacebookSession

    fun postRegisterEmailDefault(userRegister: UserRegister){
        viewModelScope.launch {
            try{
                val response = wallpaperCollectRepoImpl.userRegister(userRegister)
                _registerEmailDefaultStatus.emit(response)
            }catch (e: Exception){
                _registerEmailDefaultStatus.emit(Status(e.message.toString()))
            }
        }
    }

    fun getRegisterGoogleSession(){
        viewModelScope.launch {
            try {
                val response = wallpaperCollectRepoImpl.userGoogleRegister()
                _registerGoogleSession.emit(response)
            }catch (e : Exception){
                _registerGoogleSession.emit(Url(""))
            }
        }
    }

    fun getRegisterFacebookSession(){
        viewModelScope.launch {
            try {
                val response = wallpaperCollectRepoImpl.userFacebookRegister()
                _registerFacebookSession.emit(response)
            }catch (e :Exception){
                _registerFacebookSession.emit(Status(e.message.toString()))
            }
        }
    }

}