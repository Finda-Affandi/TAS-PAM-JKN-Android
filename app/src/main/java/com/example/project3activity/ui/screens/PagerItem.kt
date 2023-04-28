package com.example.project3activity.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent

@Composable
fun PagerItemScreen(imageUrl:String) {

    Box(modifier = Modifier.fillMaxSize())
    SubcomposeAsyncImage(model = imageUrl, contentDescription = "image",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillWidth) {
        val state = painter.state

        if(state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error){
            CircularProgressIndicator()
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}