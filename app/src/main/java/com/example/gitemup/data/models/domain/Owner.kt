package com.example.gitemup.data.models.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    @SerializedName(value = "login")
    val username: String,
    val id: Int,
    @SerializedName(value = "html_url")
    val url: String,
    @SerializedName(value = "avatar_url")
    val avatarUrl: String
): Parcelable