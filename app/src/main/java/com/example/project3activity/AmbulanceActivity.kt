package com.example.project3activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.example.project3activity.models.JknUserViewModel
import com.example.project3activity.ui.screens.Ambulance
import com.example.project3activity.ui.screens.OtherPage
import com.example.project3activity.ui.theme.Project3activityTheme

class AmbulanceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = JknUserViewModel()
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            0
        )
        setContent {
            Project3activityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val userId = getIntent().getStringExtra("userId") ?: ""
                    Ambulance(vm, userId)
                }
            }
            Column(modifier = Modifier
                .fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier.size(325.dp),shape = CircleShape,colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(
                    id = R.color.red_ambulance
                )),onClick = {
                    Intent(applicationContext, LocationService::class.java).apply {
                        action = LocationService.ACTION_START
                        startService(this)
                    }
                }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.ambulancce_icon_1),
                            contentDescription = stringResource(id = R.string.ambulance),
                            tint = Color.White,
                            modifier = Modifier.size(96.dp)
                        )


                        Spacer(modifier = Modifier.height(1.dp))
                        Text(
                            text = stringResource(id = R.string.SOS),
                            color = Color.White,
                            fontSize = 42.sp,
                            style = TextStyle(fontWeight = FontWeight.SemiBold)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4ECB71)),onClick = {
                    Intent(applicationContext, LocationService::class.java).apply {
                        action = LocationService.ACTION_STOP
                        startService(this)
                    }
                }) {
                    Text(text = "Stop", color = Color.White)
                }

            }
        }
    }
}

