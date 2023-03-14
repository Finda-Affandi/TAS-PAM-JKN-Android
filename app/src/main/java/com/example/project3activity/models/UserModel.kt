package com.example.project3activity.models

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id")
    var id: Int,

    @SerializedName("userId")
    var userId: Int,

    @SerializedName("username")
    var username: String,

    @SerializedName("password")
    var password: String,

    @SerializedName("firstname")
    var firstname: String,

    @SerializedName("lastname")
    var lastname: String
)
