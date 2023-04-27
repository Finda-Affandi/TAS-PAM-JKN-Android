package com.example.project3activity.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.project3activity.Firebase.GetFirebaseData

@Composable
fun ArticleDetails(viewModel: GetFirebaseData, articleId: String) {

    val articleData by viewModel.getArticleData(articleId!!)

//    Text(text = "Judul : ${articleData?.title}")
//    Text(text = "Deskripsi : ${articleData?.description}")
    Text(text = "Isi : ${articleData?.text}")
//    Text(text = "Tanggal : ${articleData?.pubDate}")

}