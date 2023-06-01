package com.example.wallpapercollect.presentation.viewmodels.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.Token
import com.example.wallpapercollect.api.models.Url
import com.example.wallpapercollect.api.models.UserLogIn
import com.example.wallpapercollect.repository.WallpaperCollectRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class Login @Inject constructor(
    private val wallpaperCollectRepoImpl: WallpaperCollectRepoImpl
):ViewModel() {
    private val _loginEmailDefault = MutableStateFlow(Status(""))
    private val _loginGoogleSession = MutableStateFlow(Status(""))
    private val _loginFacebookSession = MutableStateFlow(Url("",""))

    var loginEmailDefault = _loginEmailDefault
    var loginGoogleSession = _loginGoogleSession
    var loginFacebookSession = _loginFacebookSession



    fun getLoginEmailDefault(userLogin: UserLogIn){
        viewModelScope.launch {
            try {
                val response = wallpaperCollectRepoImpl.userLogin(userLogin)
                _loginEmailDefault.emit(response)
            }catch (e :Exception){
                _loginEmailDefault.emit(Status(e.message.toString()))
            }
        }
    }

    fun postLoginGoogleSession(token :Token){
        viewModelScope.launch {
            try {
                val response = wallpaperCollectRepoImpl.userGoogleLogin(token)
                _loginGoogleSession.emit(response)
            }catch (e :Exception){
                _loginGoogleSession.emit(Status(""))
            }
        }
    }

    fun getLoginFacebookSession(){
        viewModelScope.launch {
            try {
                val response = wallpaperCollectRepoImpl.userFacebookLogin()
                _loginFacebookSession.emit(response)
            }catch (e : Exception){
                _loginFacebookSession.emit(Url("",e.message.toString()))
            }
        }
    }
}