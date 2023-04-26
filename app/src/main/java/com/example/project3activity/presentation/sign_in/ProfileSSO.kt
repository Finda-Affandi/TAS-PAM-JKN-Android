package com.example.project3activity.presentation.sign_in

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOut: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(), 
        verticalArrangement = Arrangement.Center, 
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(userData?.profilePictureUrl != null){
            AsyncImage(
                model = userData.profilePictureUrl ,
                contentDescription = "Profile Picture",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier.height(16.dp))
        }

        if(userData?.firstname != null) {
            Text(text = userData.firstname, textAlign = TextAlign.Center, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 36.sp))
            Spacer(modifier = Modifier.height(16.dp))
        }

        if(userData?.username != null) {
            Text(text = userData.username, textAlign = TextAlign.Center, style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 24.sp))
            Spacer(modifier = Modifier.height(24.dp))
        }

        Button(onClick = onSignOut) {
            Text(text = "Sign Out")

        }
        


    }
}