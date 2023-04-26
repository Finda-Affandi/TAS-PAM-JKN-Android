package com.example.project3activity.ui.screens

import android.widget.Toast
import androidx.compose.ui.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.project3activity.presentation.sign_in.SignInState
//import java.lang.reflect.Modifier

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInCLick: () -> Unit
) {

    val lContext = LocalContext.current
    
    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let  {
            error ->
            Toast.makeText(lContext, error, Toast.LENGTH_LONG).show()
        }
    }
    
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), contentAlignment = Alignment.Center){
        Button(onClick = onSignInCLick) {
            Text(text = "Sign In")
            
        }
    }

}