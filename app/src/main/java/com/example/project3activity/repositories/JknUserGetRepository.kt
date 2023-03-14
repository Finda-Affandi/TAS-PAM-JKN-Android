package com.example.project3activity.repositories

import com.example.project3activity.models.JknUserModel
import com.example.project3activity.models.UserModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JknUserGetRepository {
    @GET("jkn")
    suspend fun getJknUser(): List<JknUserModel>

    companion object{
        var _apiClient: JknUserGetRepository? = null

        fun getClient(): JknUserGetRepository {
            if(_apiClient == null) {
                _apiClient = Retrofit.Builder()
                    .baseUrl("http://103.131.18.183:3000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(JknUserGetRepository::class.java)
            }
            return _apiClient!!
        }
    }
}