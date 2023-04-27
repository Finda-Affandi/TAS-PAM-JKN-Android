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
import com.example.project3activity.Firebase.GetFirebaseData
import com.example.project3activity.ui.screens.ArticleDetails
import com.example.project3activity.ui.screens.DoctorConsultationDetails
import com.example.project3activity.ui.theme.Project3activityTheme

class FormKonsulActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val getData = GetFirebaseData()
        super.onCreate(savedInstanceState)
        setContent {
            Project3activityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val DoctorId = getIntent().getStringExtra("DoctorId") ?: ""
                    DoctorConsultationDetails(getData, DoctorId)
//                    Greeting3("Android")
                }
            }
        }
    }
}
//
//@Composable
//fun Greeting3(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview2() {
//    Project3activityTheme {
//        Greeting3("Android")
//    }
//}