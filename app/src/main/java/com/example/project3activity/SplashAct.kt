package com.example.project3activity

//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import android.content.Intent
import android.os.Bundle
import android.os.Handler
//import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.project3activity.presentation.sign_in.GoogleAuthUiClient
import com.example.project3activity.ui.screens.navigation
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay


class SplashAct : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        val currentUser = FirebaseAuth.getInstance().currentUser
        super.onCreate(savedInstanceState)
        setContent {
            val lCOntext = LocalContext.current
            LaunchedEffect(navigation()){
                delay(1500L)
                if(currentUser != null || googleAuthUiClient.getSignedInUser() != null){
                lCOntext.startActivity(
                    Intent(lCOntext, HomeActivity::class.java)
//                        .putExtra("username", username)
                )
                }
                else{
                    lCOntext.startActivity(
                        Intent(lCOntext, MainActivity::class.java)
                    )
                }
            }
            }
        }
    }

