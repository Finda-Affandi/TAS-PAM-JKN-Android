package com.example.project3activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.project3activity.ui.screens.SkrinningProccesScreen
import com.example.project3activity.ui.theme.Project3activityTheme

class SkrinningProccessActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Project3activityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Greeting2("Android")
                    val userId = getIntent().getStringExtra("userId") ?: ""
                    val check = getIntent().getStringExtra("check") ?: ""
                    SkrinningProccesScreen(userId, check)
                }
            }
        }
    }


}
