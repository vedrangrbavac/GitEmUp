package com.example.gitemup.data.models.domain

import com.google.gson.annotations.SerializedName

data class ResponseWrapper(
    @SerializedName(value = "total_count")
    val totalCount: Int,
    @SerializedName(value = "incomplete_results")
    val incompleteResults: Boolean,
    val items: List<Item>

)