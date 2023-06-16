package com.example.wallpapercollect.presentation.viewmodel.wallpaperpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapercollect.api.models.ImagesCollections
import com.example.wallpapercollect.api.models.Status
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
    private val _isUploadCompleted = MutableStateFlow(true)
    private val _wallpaperCollection = MutableStateFlow(ImagesCollections(ArrayList(),""))
    private val _wallpaperUploadStatus = MutableStateFlow(Status(""))
    private val _wallpaperDeleteStatus = MutableStateFlow(Status(""))

    val isLoading = _isLoading.asStateFlow()
    val isUploadCompleted = _isUploadCompleted.asStateFlow()
    val wallpaperCollection = _wallpaperCollection
    val wallpaperUploadStatus = _wallpaperUploadStatus
    var wallpaperDeleteStatus = _wallpaperDeleteStatus

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
                _wallpaperCollection.emit(ImagesCollections(ArrayList(),e.localizedMessage?:""))
            }
            _isLoading.value = false
        }
    }

    fun wallpaperUpload(image : MultipartBody.Part){
        viewModelScope.launch {
            _isUploadCompleted.value = false
            try {
                val response = wallpaperCollectRepoImpl.wallpaperUpload(image)
                _wallpaperUploadStatus.emit(response)
            } catch (e :Exception){
                _wallpaperUploadStatus.emit(Status(e.localizedMessage?:""))
            }
            _isUploadCompleted.value = true
        }
    }

    fun wallpaperDelete(id:String){
        viewModelScope.launch {
            try {
                val response = wallpaperCollectRepoImpl.deleteImage(id)
                _wallpaperDeleteStatus.emit(response)
            } catch (e :Exception){
                _wallpaperDeleteStatus.emit(Status(e.localizedMessage?:""))
            }
        }
    }
}