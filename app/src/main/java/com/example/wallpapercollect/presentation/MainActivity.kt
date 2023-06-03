package com.example.wallpapercollect.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.wallpapercollect.R
import com.example.wallpapercollect.presentation.ui.navigation.WallpaperCollectAppNavHost
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            supportActionBar?.hide()

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(stringResource(R.string.google_token))
                .requestProfile()
                .requestEmail()
                .build()

            val gsc = GoogleSignIn.getClient(this,gso)

            WallpaperCollectAppNavHost(gsc = gsc)
        }
    }
}




