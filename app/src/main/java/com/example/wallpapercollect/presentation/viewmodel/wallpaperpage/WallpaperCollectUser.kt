package com.example.wallpapercollect.presentation.viewmodel.wallpaperpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapercollect.api.models.ImagesCollections
import com.example.wallpapercollect.api.models.Status
import com.example.wallpapercollect.api.models.UrlAndId
import com.example.wallpapercollect.repository.WallpaperCollectRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject


@HiltViewModel
class WallpaperCollectUser @Inject constructor(
    private val wallpaperCollectRepoImpl: WallpaperCollectRepoImpl
): ViewModel() {
    private val _wallpaperCollection = MutableStateFlow(ImagesCollections(ArrayList(),""))
    private val _wallpaperUpload = MutableStateFlow<Status?>(null)

    val wallpaperCollection = _wallpaperCollection
    val wallpaperUploadStatus = _wallpaperUpload

    fun getWallpaperCollection(){
        viewModelScope.launch {
            try {
                val response = wallpaperCollectRepoImpl.wallpaperCollection()
                _wallpaperCollection.emit(response)
            }catch (e :Exception){
                _wallpaperCollection.emit(ImagesCollections(ArrayList(),e.message.toString()))
            }
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