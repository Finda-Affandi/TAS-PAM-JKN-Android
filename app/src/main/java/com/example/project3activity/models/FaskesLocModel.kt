package com.example.project3activity.models

import com.google.gson.annotations.SerializedName

data class FaskesLocModel(
    @SerializedName("namatempat")
    var name: String,
    @SerializedName("alamat")
    var address: String
)
