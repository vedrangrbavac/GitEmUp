package com.example.gitemup.data.models.domain

import com.google.gson.annotations.SerializedName

data class Item(
    val id: Int,
    val name: String,
    @SerializedName(value = "full_name")
    val fullName: String,
    @SerializedName(value = "private")
    val isPrivate: Boolean,
    val owner: Owner,
    val description: String,
    @SerializedName(value = "html_url")
    val url: String,
    val language: String
)