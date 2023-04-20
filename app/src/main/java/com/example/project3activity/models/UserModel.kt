package com.example.project3activity.models

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id")
    var uid: String = "",

    @SerializedName("firstname")
    var firstname: String = "",

    @SerializedName("lastname")
    var lastname: String = ""
)
