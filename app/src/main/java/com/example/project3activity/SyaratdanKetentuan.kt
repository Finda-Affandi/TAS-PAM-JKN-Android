package com.example.project3activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.example.project3activity.ui.screens.SyaratdanKetentuanScreen
import com.example.project3activity.ui.theme.Project3activityTheme

@Suppress("DEPRECATION")
class SyaratdanKetentuan : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = resources.configuration
        val language = config.locale.language
        setContent {
            Project3activityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Greeting2("Android")
                    val userId = getIntent().getStringExtra("userId") ?: ""
                    SyaratdanKetentuanScreen(userId, language)
                }
            }
        }
    }


}
