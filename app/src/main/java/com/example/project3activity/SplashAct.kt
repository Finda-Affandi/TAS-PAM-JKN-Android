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
import com.example.project3activity.ui.screens.navigation
import kotlinx.coroutines.delay


class SplashAct : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val lCOntext = LocalContext.current
            LaunchedEffect(navigation()){
                delay(1500L)
                lCOntext.startActivity(
                    Intent(lCOntext, MainActivity::class.java)
                )
            }
            }
        }
    }

