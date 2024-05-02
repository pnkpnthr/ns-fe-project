package com.example.ns_fe_project.model

import com.google.gson.annotations.SerializedName

data class Departments constructor(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("imageUrl") val imageUrl: String?
)
