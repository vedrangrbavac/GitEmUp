package com.example.gitemup.data.models.domain

import com.google.gson.annotations.SerializedName
import java.util.*

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
    val language: String,
    @SerializedName(value = "forks_count")
    val numberOfForks: Int,
    @SerializedName(value = "stargazers_count")
    val numberOfStarts: Int,
    @SerializedName(value = "watchers_count")
    val numberOfWatchers:Int,
    @SerializedName(value = "open_issues_count")
    val numberOfIssues: Int,
    @SerializedName(value = "updated_at")
    val updated: Date
)