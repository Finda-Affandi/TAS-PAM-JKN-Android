package com.example.project3activity.repositories

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ImagePostRepository {
    @POST("image")
    fun addImage(@Body Image : ImageModel): Call<ImageModel>
}