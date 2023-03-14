package com.example.project3activity.models

import com.google.gson.annotations.SerializedName

data class JknUserModel(
    @SerializedName("id")
    var id: Int,

    @SerializedName("firstname")
    var firstname: String,

    @SerializedName("lastname")
    var lastname: String,

    @SerializedName("nik")
    var nik: String,

    @SerializedName("lahir")
    var lahir: String,

    @SerializedName("alamat")
    var alamat: String,
)
