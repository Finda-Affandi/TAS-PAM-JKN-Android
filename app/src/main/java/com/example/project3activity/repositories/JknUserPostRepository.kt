package com.example.project3activity.repositories

import com.example.project3activity.models.JknUserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface JknUserPostRepository {
    @POST("jkn")
    fun addJknUser(@Body JknUser : JknUserModel): Call<JknUserModel>
}