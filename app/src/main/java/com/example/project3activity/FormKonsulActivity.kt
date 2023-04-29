package com.example.project3activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.project3activity.Firebase.GetFirebaseData
import com.example.project3activity.ui.screens.ArticleDetails
import com.example.project3activity.ui.screens.DoctorConsultationDetails
import com.example.project3activity.ui.theme.Project3activityTheme
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

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
                    var pickedDate by remember {
                        mutableStateOf(LocalDate.now())
                    }

                    var pickedTime by remember {
                        mutableStateOf(LocalTime.now())
                    }

                    val formattedDate by remember {
                        derivedStateOf {
                            DateTimeFormatter
                                .ofPattern("dd MMM yyy").format(pickedDate)
                        }
                    }

                    val formattedTime by remember {
                        derivedStateOf {
                            DateTimeFormatter
                                .ofPattern("hh:mm").format(pickedTime)
                        }
                    }

                    val DoctorId = getIntent().getStringExtra("DoctorId") ?: ""
                    DoctorConsultationDetails(getData, DoctorId)
//                    Greeting3("Android")
                    
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                    }
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