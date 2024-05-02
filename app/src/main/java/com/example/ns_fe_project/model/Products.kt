package com.example.ns_fe_project.model

import com.google.gson.annotations.SerializedName

data class Products constructor(
    @SerializedName("name") val name: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("desc") val desc: String,
    @SerializedName("price") val price: String,
    @SerializedName("type") val type: String,
    @SerializedName("id") val id: String,
    @SerializedName("departmentId") val departmentId: String
)
