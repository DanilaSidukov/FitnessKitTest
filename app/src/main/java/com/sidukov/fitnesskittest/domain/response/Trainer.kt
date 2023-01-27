package com.sidukov.fitnesskittest.domain.response

import com.google.gson.annotations.SerializedName

data class Trainer(
    @SerializedName("description")
    val description: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("image_url_medium")
    val image_url_medium: String,
    @SerializedName("image_url_small")
    val image_url_small: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("position")
    val position: String
)