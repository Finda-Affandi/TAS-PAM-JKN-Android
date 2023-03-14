package com.example.project3activity.repositories

import com.example.project3activity.models.ArticleModel
import com.example.project3activity.models.UserModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ArticleGetRepository {
    @GET("article")
    suspend fun getArticle(): List<ArticleModel>

    companion object{
        var _apiClient: ArticleGetRepository? = null

        fun getClient(): ArticleGetRepository {
            if(_apiClient == null) {
                _apiClient = Retrofit.Builder()
                    .baseUrl("http://103.131.18.183:3000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ArticleGetRepository::class.java)
            }
            return _apiClient!!
        }
    }
}