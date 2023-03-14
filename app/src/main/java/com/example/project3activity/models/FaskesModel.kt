package com.example.project3activity.models

import com.google.gson.annotations.SerializedName

data class FaskesModel(
    @SerializedName("id")
    var id: Int,

    @SerializedName("nama")
    var nama: String,

    @SerializedName("alamat")
    var alamat: String,

    @SerializedName("image")
    var image: String
)
