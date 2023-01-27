package com.sidukov.fitnesskittest.domain.response

import com.google.gson.annotations.SerializedName

data class Tab(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)