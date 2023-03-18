package com.example.project3activity.models

import com.example.project3activity.repositories.JknUserPostRepository
import com.example.project3activity.repositories.UserPostRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object JknUserServiceBuilder {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.26.198:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(JknUserPostRepository::class.java)
}