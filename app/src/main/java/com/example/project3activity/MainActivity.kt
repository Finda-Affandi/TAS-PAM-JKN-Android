package com.example.project3activity

//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.project3activity.models.UserViewModel
import com.example.project3activity.ui.screens.LoginForm
import com.example.project3activity.ui.screens.navigation
import com.example.project3activity.ui.theme.Project3activityTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = UserViewModel()
        super.onCreate(savedInstanceState)
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(
//                android.Manifest.permission.ACCESS_COARSE_LOCATION,
//                android.Manifest.permission.ACCESS_FINE_LOCATION,
//            ),
//            0
//        )
        setContent {
            Project3activityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginForm(vm)
                }
//                Column(modifier = Modifier
//                    .fillMaxSize()
//                    .padding(bottom = 550.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
//                    Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4ECB71)),onClick = {
//                        Intent(applicationContext, LocationService::class.java).apply {
//                            action = LocationService.ACTION_START
//                            startService(this)
//                        }
//                    }) {
//                        Text(text = "Get Location", color = Color.White)
//                    }
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4ECB71)),onClick = {
//                        Intent(applicationContext, LocationService::class.java).apply {
//                            action = LocationService.ACTION_STOP
//                            startService(this)
//                        }
//                    }) {
//                        Text(text = "Stop", color = Color.White)
//                    }
//                }
            }
        }
    }
}

