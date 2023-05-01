package com.example.project3activity.repositories

import com.example.project3activity.models.FaskesLocModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JSONPlaceholderTypicodeRepository {
    @GET("Location")
    suspend fun getFaskesLoc():List<FaskesLocModel>

    companion object{
        var _apiClient: JSONPlaceholderTypicodeRepository? = null


        fun getClient(): JSONPlaceholderTypicodeRepository{
            if(_apiClient == null){
                _apiClient = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/himichael00/webserviceTASPAM/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(JSONPlaceholderTypicodeRepository::class.java)
            }

            return _apiClient!!;
        }

    }
}