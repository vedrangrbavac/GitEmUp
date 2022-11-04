package com.example.gitemup.data.models.domain

import com.google.gson.annotations.SerializedName


data class Owner(
    @SerializedName(value = "login")
    val username: String,
    val id: Int,
    @SerializedName(value = "html_url")
    val url: String,
    @SerializedName(value = "avatar_url")
    val avatarUrl: String
)