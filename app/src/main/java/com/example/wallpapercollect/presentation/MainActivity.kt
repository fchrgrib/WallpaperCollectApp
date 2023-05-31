package com.example.wallpapercollect.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.wallpapercollect.presentation.ui.navigation.WallpaperCollectAppNavHost
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
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
//            var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("379189349708-5pgkr1aka7dbe03nqigd78p4m81ajnvr.apps.googleusercontent.com")
//                .requestEmail()
//                .requestProfile()
//                .build()
//
//            val gsc = GoogleSignIn.getClient(this,gso)
//            val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
//
//            val testing = gsc.signInIntent
//            nyobaDoangWir(
//                onClick = {startActivityForResult(testing,1000)},
//                token = account!!.idToken
//            )
        }
    }
}


@Composable
fun nyobaDoangWir(
    onClick: () -> Unit,
    token: String?
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onClick) {
            Text("klik ini wir")
        }
    }
    Text("token" + token ?: "salah")
}



