package com.example.project3activity.models

import com.google.gson.annotations.SerializedName

data class RecentModel(
    @SerializedName("id")
    var id: Int,

    @SerializedName("location")
    var location: String,

    @SerializedName("doctor")
    var doctor: String,

    @SerializedName("type")
    var type: String,

    @SerializedName("diag")
    var diag: String,
)
