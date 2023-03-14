package com.example.project3activity.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project3activity.MainActivity
import kotlinx.coroutines.delay
import com.example.project3activity.R
import com.example.project3activity.ui.theme.Project3activityTheme


@Composable
fun navigation() {
    val lCOntext = LocalContext.current
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash_screen"){

        composable("splash_screen"){
            SplashScreen(navController = navController)
        }

        composable("main_screen"){
            lCOntext.startActivity(
                Intent(lCOntext, MainActivity::class.java)
            )
        }
    }
}


@Composable
fun SplashScreen(navController: NavController){
    LaunchedEffect(key1 = true){
        delay(3000L)
//        navController.navigate("main_screen")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_form),
            contentDescription = "logo",
            modifier = Modifier
                .width(240.dp)
                .height(240.dp)
        )
    }
}

@Composable
@Preview
fun splash() {
    Project3activityTheme {
        navigation()
    }
}