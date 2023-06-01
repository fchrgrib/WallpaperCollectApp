package com.example.wallpapercollect.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.wallpapercollect.presentation.ui.navigation.WallpaperCollectAppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var instance: MainActivity
            private set
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            supportActionBar?.hide()
            instance = this
            WallpaperCollectAppNavHost()
        }
    }
}




