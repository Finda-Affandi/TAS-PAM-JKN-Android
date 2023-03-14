package com.example.project3activity.models

import com.google.gson.annotations.SerializedName

data class ArticleModel(
    @SerializedName("id")
    var id: Int,

    @SerializedName("language")
    var language: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("authors")
    var authors: String,

    @SerializedName("time")
    var time: String,

    @SerializedName("content")
    var content: String,
)
