package com.example.project3activity.repositories

import com.example.project3activity.models.FaskesLocModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JSONPlaceholderTypicode {
    @GET("Location")
    suspend fun getFaskesLoc():List<FaskesLocModel>

    companion object{
        var apiClient: JSONPlaceholderTypicode? = null


        fun getClient(): JSONPlaceholderTypicode{
            if(apiClient == null){
                apiClient = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/himichael00/webserviceTASPAM/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(JSONPlaceholderTypicode::class.java)
            }

            return apiClient!!
        }
    }
}