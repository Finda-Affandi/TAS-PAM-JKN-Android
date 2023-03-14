package com.example.project3activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project3activity.models.UserViewModel
import com.example.project3activity.ui.screens.Signup
import com.example.project3activity.ui.theme.Project3activityTheme

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val vm = UserViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            Project3activityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

//                    Greeting2("Android")
                    Signup(::sendUsernameBackToLogin, vm)
                }
            }
        }
    }

    private fun sendUsernameBackToLogin(username: String?) {
        val result = Intent().putExtra("username", username)
        setResult(Activity.RESULT_OK, result)
        finish()
    }
}



@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

