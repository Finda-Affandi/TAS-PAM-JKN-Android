package com.example.project3activity.repositories

import com.example.project3activity.models.UserModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserGetRepository {
    @GET("user")
    suspend fun getUser(): List<UserModel>

    companion object{
        var _apiClient: UserGetRepository? = null

        fun getClient(): UserGetRepository {
            if(_apiClient == null) {
                _apiClient = Retrofit.Builder()
                    .baseUrl("http://103.131.18.183:3000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UserGetRepository::class.java)
            }
            return _apiClient!!
        }
    }
}