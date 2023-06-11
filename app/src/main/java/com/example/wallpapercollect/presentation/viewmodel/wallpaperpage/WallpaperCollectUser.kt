package com.example.wallpapercollect.presentation.viewmodel.wallpaperpage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapercollect.api.models.ImagesCollections
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.UrlAndId
import com.example.wallpapercollect.repository.WallpaperCollectRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject


@HiltViewModel
class WallpaperCollectUser @Inject constructor(
    private val wallpaperCollectRepoImpl: WallpaperCollectRepoImpl
): ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    private val _wallpaperCollection = MutableStateFlow(ImagesCollections(ArrayList(),""))
    private val _wallpaperUpload = MutableStateFlow(Status(""))

    val wallpaperCollection = _wallpaperCollection
    val isLoading = _isLoading.asStateFlow()
    val wallpaperUploadStatus = _wallpaperUpload

    init {
        getWallpaperCollection()
    }

    fun getWallpaperCollection(){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = wallpaperCollectRepoImpl.wallpaperCollection()
                _wallpaperCollection.emit(response)
            }catch (e :Exception){
                _wallpaperCollection.emit(ImagesCollections(ArrayList(),e.message.toString()))
            }
            _isLoading.value = false
        }
    }

    fun wallpaperUpload(image : MultipartBody.Part){
        viewModelScope.launch {
            try {
                val response = wallpaperCollectRepoImpl.wallpaperUpload(image)
                _wallpaperUpload.emit(response)
            } catch (e :Exception){
                _wallpaperUpload.emit(Status(e.message.toString()))
            }
        }
    }
}