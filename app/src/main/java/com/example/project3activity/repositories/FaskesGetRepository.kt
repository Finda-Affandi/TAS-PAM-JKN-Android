package com.example.project3activity.repositories

import com.example.project3activity.models.ArticleModel
import com.example.project3activity.models.FaskesModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface FaskesGetRepository {
    @GET("faskes")
    suspend fun getFaskes(): List<FaskesModel>

    companion object{
        var _apiClient: FaskesGetRepository? = null

        fun getClient(): FaskesGetRepository {
            if(_apiClient == null) {
                _apiClient = Retrofit.Builder()
                    .baseUrl("http://192.168.26.198:3000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(FaskesGetRepository::class.java)
            }
            return _apiClient!!
        }
    }
}