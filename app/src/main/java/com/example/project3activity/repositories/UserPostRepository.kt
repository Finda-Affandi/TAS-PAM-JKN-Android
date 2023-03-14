package com.example.project3activity.repositories

import com.example.project3activity.models.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserPostRepository {
    @POST("user")
    fun addUser(@Body userData : UserModel): Call<UserModel>
}