package com.example.project3activity.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ImageGetRepository {
    @GET("image")
    suspend fun getImage(): List<ImageModel>

    companion object{
        var _apiClient: ImageGetRepository? = null

        fun getClient(): ImageGetRepository {
            if(_apiClient == null) {
                _apiClient = Retrofit.Builder()
                    .baseUrl("http://103.131.18.183:3000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ImageGetRepository::class.java)
            }
            return _apiClient!!
        }
    }
}