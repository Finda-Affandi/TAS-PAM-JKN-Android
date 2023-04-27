package com.example.project3activity.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RecentGetRepository {
    @GET("recent")
    suspend fun getRecent(): List<RecentModel>

    companion object{
        var _apiClient: RecentGetRepository? = null

        fun getClient(): RecentGetRepository {
            if(_apiClient == null) {
                _apiClient = Retrofit.Builder()
                    .baseUrl("http://103.131.18.183:3000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RecentGetRepository::class.java)
            }
            return _apiClient!!
        }
    }
}