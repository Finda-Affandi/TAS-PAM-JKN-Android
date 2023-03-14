package com.example.project3activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.project3activity.models.JknUserViewModel
import com.example.project3activity.ui.screens.Ambulance
import com.example.project3activity.ui.screens.Konsul
import com.example.project3activity.ui.theme.Project3activityTheme

class ConsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = JknUserViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            Project3activityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val userId = getIntent().getStringExtra("userId") ?: ""
                    Konsul(vm, userId)
                }
            }
        }
    }
}
