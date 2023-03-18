package com.example.project3activity.models

import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("id")
    var id: Int,

    @SerializedName("url")
    var url: String,
)
