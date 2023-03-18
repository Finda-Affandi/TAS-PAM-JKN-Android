package com.example.project3activity.models

import com.example.project3activity.repositories.ImagePostRepository
import com.example.project3activity.repositories.JknUserPostRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ImageServiceBuilder {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.26.198:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ImagePostRepository::class.java)
}