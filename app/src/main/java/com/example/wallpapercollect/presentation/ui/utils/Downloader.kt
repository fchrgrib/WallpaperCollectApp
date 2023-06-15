package com.example.wallpapercollect.presentation.ui.utils

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.core.net.toUri

class Downloader(
    private val context: Context
) {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    fun downloadFile(url :String,imageName:String): Long{

        val requestDownload = DownloadManager.Request(url.toUri())
            .setMimeType("image/*")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
            .setTitle(imageName)
            .setDestinationInExternalFilesDir(context,Environment.DIRECTORY_DOWNLOADS,imageName)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDescription("Downloading...")
        Toast.makeText(context,"Download $imageName started",Toast.LENGTH_LONG).show()
        return downloadManager.enqueue(requestDownload)
    }
}